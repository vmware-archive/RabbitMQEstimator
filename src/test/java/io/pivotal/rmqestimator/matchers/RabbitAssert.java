package io.pivotal.rmqestimator.matchers;

import com.google.common.base.*;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

import io.pivotal.rmqestimator.rest.mgmt.model.*;
import io.pivotal.rmqestimator.rest.mgmt.model.federation.FederationLink;
import io.pivotal.rmqestimator.service.RabbitNodeMgmtService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.*;

/**
 * This is a tool to help developers using RabbitMQ make assertions about their current topology configuration.
 *
 * The assertion framework was created to support my work in verifying RabbitMQ deployments.  It allows us
 * to write integration tests to verify an environment is correctly setup before it is rolled into production.
 *
 * The assertion framework is designed to do various things:
 *
 * 1.  Determine if topology items exist, or to verify they do not exist, in a RabbitMQ cluster.  You would
 *     most likely use this to verify exchanges or queues exist, or that bindings between them are intact.
 * 2.  Verify that complex routing scenarios actually allow messages to be delivered to the correct queues
 *     instead of being dropped or delivered to unintended recipients.  This is a really important use case
 *     for us because of the complex topologies we support.
 *
 * @author Richard Clayton (Berico Technologies)
 */
public class RabbitAssert {

    private RabbitNodeMgmtService mgmt;

    /**
     * Initialize the Assertion framework with a pre-configured management instance.
     * @param mgmt pre-configured management instance.
     */
    public RabbitAssert(RabbitNodeMgmtService mgmt){
        this.mgmt = mgmt;
    }

    /**
     * Assert that RabbitMQ has the specified node.
     * @param nodeName Name of the node to ensure exists.
     * @param matchers Modifies the assertion by adding criteria to match.
     * @return this.
     */
    public RabbitAssert hasNode(String nodeName, NodeMatcher... matchers){

        Optional<Node> node = mgmt.nodes().get(nodeName);

        assertTrue(String.format("Node '%s' does not exist.", nodeName), node.isPresent());

        if (matchers != null && matchers.length > 0) {

            MatchResult result = isMatch(node.get(), matchers);

            assertTrue(result.getReason(), result.isMatch());
        }

        return this;
    }

    /**
     * Assert that RabbitMQ does not have the specified node.
     * @param nodeName Name of the node that should not exist.
     * @param matchers Modifies the assertion by adding criteria to match.
     * @return this.
     */
    public RabbitAssert doesNotHaveNode(String nodeName, NodeMatcher... matchers){

        Optional<Node> node = mgmt.nodes().get(nodeName);

        if (node.isPresent()){

            if (matchers != null && matchers.length > 0) {

                MatchResult result = isMatch(node.get(), matchers);

                assertFalse(result.getReason(), result.isMatch());
            }
            else {

                fail(String.format("Node '%s' should not exist but does.", nodeName));
            }
        }

        return this;
    }

    /**
     * Assert that RabbitMQ has the specified Virtual Host.
     * @param vhostName VHost that should exist.
     * @return this.
     */
    public RabbitAssert hasVHost(String vhostName){

        Optional<VirtualHost> vhost = mgmt.vhosts().get(vhostName);

        assertTrue(String.format("VHost '%s' should exist but does not.", vhostName), vhost.isPresent());

        return this;
    }

    /**
     * Assert that the specified Virtual Host does not exist in the RabbitMQ cluster.
     * @param vhostName Name of the vhost that should not exist.
     * @return this.
     */
    public RabbitAssert doesNotHaveVHost(String vhostName){

        Optional<VirtualHost> vhost = mgmt.vhosts().get(vhostName);

        assertFalse(String.format("VHost '%s' should not exist but does.", vhostName), vhost.isPresent());

        return this;
    }

    /**
     * Assert that RabbitMQ has the specified User.
     * @param username Name of the user that should exist.
     * @return this.
     */
    public RabbitAssert hasUser(String username){

        Optional<User> user = mgmt.users().get(username);

        assertTrue(String.format("User '%s' should exist but does not.", username), user.isPresent());

        return this;
    }

    /**
     * Assert that RabbitMQ does not have the specified user.
     * @param username Name of the user that should not exist.
     * @return this.
     */
    public RabbitAssert doesNotHaveUser(String username){

        Optional<User> user = mgmt.users().get(username);

        assertFalse(String.format("User '%s' should not exist but does.", username), user.isPresent());

        return this;
    }

    /**
     * Assert that the current authenticated user (interacting with the Management Console) is
     * the one specified.
     * @param username Name of the user that should be interacting with the console.
     * @return this.
     */
    public RabbitAssert iAm(String username){

        User clientUser = mgmt.users().whoAmI();

        assertEquals(String.format("Current user should be '%s', but is actually '%s'.",
                username, clientUser.getName()), username, clientUser.getName());

        return this;
    }

    /**
     * Assert that the specified user has the specified tags.
     * @param username Name of the user.
     * @param expectedTags Tags that the user should have.
     * @return this.
     */
    public RabbitAssert userHasTags(String username, String... expectedTags){

        Preconditions.checkNotNull(expectedTags);
        Preconditions.checkArgument(expectedTags.length > 0);

        Optional<User> user = mgmt.users().get(username);

        assertTrue(String.format("User '%s' does not exist and should.", username), user.isPresent());

        String tagCsv = user.get().getTags();

        if (!Strings.isNullOrEmpty(tagCsv)){

            List<String> tagList = splitTags(tagCsv);

            assertTrue(String.format("User '%s' does not have the tags [%s]; current tags are [%s].",
                    username, tagCsv, Joiner.on(",").join(expectedTags)),
                    tagList.containsAll(Arrays.asList(expectedTags)));
        }
        else {

            fail(String.format("User '%s' does not have any tags.", username));
        }

        return this;
    }

    /**
     * Assert that the user does not have the specified tags.
     * @param username Name of the user.
     * @param notExpectedTags Tags that should not exist.
     * @return this.
     */
    public RabbitAssert userNotHaveTags(String username, String... notExpectedTags){

        Preconditions.checkNotNull(notExpectedTags);
        Preconditions.checkArgument(notExpectedTags.length > 0);

        Optional<User> user = mgmt.users().get(username);

        assertTrue(String.format("User '%s' does not exist and should.", username), user.isPresent());

        String tagCsv = user.get().getTags();

        if (!Strings.isNullOrEmpty(tagCsv)){

            List<String> tagList = splitTags(tagCsv);

            for (String notExpectedTag : notExpectedTags)
                assertFalse(
                        String.format("User '%s' has tag '%s' and should not.", username, notExpectedTag),
                        tagList.contains(notExpectedTag));
        }

        return this;
    }

    /**
     * Assert that the User has the specified read permission.
     * @param user Name of the user.
     * @param permissionExpression Value of the permission.
     * @return this.
     */
    public RabbitAssert userHasReadPermission(String user, String permissionExpression){

        return this.userHasPermission("/", user, permissionExpression, 1);
    }

    /**
     * Assert that the User has the specified read permission.
     * @param vhost Virtual Host with the permission.
     * @param user Name of the user.
     * @param permissionExpression Value of the permission.
     * @return this.
     */
    public RabbitAssert userHasReadPermission(String vhost, String user, String permissionExpression){

        return this.userHasPermission(vhost, user, permissionExpression, 1);
    }

    /**
     * Assert that the User has the specified write permission.
     * @param user Name of the user.
     * @param permissionExpression Value of the permission.
     * @return this.
     */
    public RabbitAssert userHasWritePermission(String user, String permissionExpression){

        return this.userHasPermission("/", user, permissionExpression, 2);
    }

    /**
     * Assert that the User has the specified write permission.
     * @param vhost Virtual Host with the permission.
     * @param user Name of the user.
     * @param permissionExpression Value of the permission.
     * @return this.
     */
    public RabbitAssert userHasWritePermission(String vhost, String user, String permissionExpression){

        return this.userHasPermission(vhost, user, permissionExpression, 2);
    }

    /**
     * Assert that the User has the specified configure permission.
     * @param user Name of the user.
     * @param permissionExpression Value of the permission.
     * @return this.
     */
    public RabbitAssert userHasConfigurePermission(String user, String permissionExpression){

        return this.userHasPermission("/", user, permissionExpression, 3);
    }

    /**
     * Assert that the User has the specified configure permission.
     * @param vhost Virtual Host with the permission.
     * @param user Name of the user.
     * @param permissionExpression Value of the permission.
     * @return this.
     */
    public RabbitAssert userHasConfigurePermission(String vhost, String user, String permissionExpression){

        return this.userHasPermission(vhost, user, permissionExpression, 3);
    }


    private RabbitAssert userHasPermission(String vhost, String user, String permissionExpression, int permissionType){

        Optional<Permission> permission = mgmt.permissions().get(vhost, user);

        assertTrue(String.format("User '%s' does not have permission '%s' on vhost '%s'",
                user, permissionExpression, vhost),
                permission.isPresent());

        String actualPermission;
        String permissionTypeDescription;

        switch (permissionType){
            case 1:
                actualPermission =  permission.get().getRead();
                permissionTypeDescription = "read";
                break;
            case 2:
                actualPermission = permission.get().getWrite();
                permissionTypeDescription = "write";
                break;
            default:
                actualPermission = permission.get().getConfigure();
                permissionTypeDescription = "configure";
                break;
        }

        assertEquals(String.format("User '%s' permission '%s' should be '%s' but is '%s' on vhost '%s'.",
                user, permissionTypeDescription, permissionExpression, actualPermission, vhost),
                permissionExpression, actualPermission);

        return this;
    }

    /**
     * Assert that RabbitMQ has the specified exchange.
     * @param exchangeName Name of the Exchange on the default virtual host.
     * @param matchers Modifies the assertion by adding criteria to match.
     * @return this.
     */
    public RabbitAssert hasExchange(String exchangeName, ExchangeMatcher... matchers){

        return hasExchange("/", exchangeName, matchers);
    }

    /**
     * Assert that RabbitMQ has the specified exchange.
     * @param vhost Virtual Host with the exchange.
     * @param exchangeName Name of the exchange.
     * @param matchers Modifies the assertion by adding criteria to match.
     * @return this.
     */
    public RabbitAssert hasExchange(String vhost, String exchangeName, ExchangeMatcher... matchers){

        Optional<Exchange> exchange = mgmt.exchanges().get(vhost, exchangeName);

        assertTrue(String.format("Exchange '%s' does not exist on vhost '%s'.", exchangeName, vhost),
                exchange.isPresent());

        if (matchers != null && matchers.length > 0) {

            MatchResult result = isMatch(exchange.get(), matchers);

            assertTrue(result.getReason(), result.isMatch());
        }

        return this;
    }

    /**
     * Assert that RabbitMQ does not have the following exchange.
     * @param exchangeName Name of the exchange.
     * @return this.
     */
    public RabbitAssert doesNotHaveExchange(String exchangeName){

        return this.doesNotHaveExchange("/", exchangeName);
    }

    /**
     * Assert that RabbitMQ does not have the following exchange.
     * @param vhost Name of the vhost with the exchange.
     * @param exchangeName Name of the exchange.
     * @return this.
     */
    public RabbitAssert doesNotHaveExchange(String vhost, String exchangeName){

        Optional<Exchange> exchange = mgmt.exchanges().get(vhost, exchangeName);

        assertFalse(
                String.format("Exchange '%s' exists and should not on vhost '%s'.", exchangeName, vhost),
                exchange.isPresent());

        return this;
    }

    /**
     * Assert that RabbitMQ has the specified queue on the default virtual host.
     * @param queueName Name of the Queue.
     * @param matchers Modifies the assertion by adding criteria to match.
     * @return this.
     */
    public RabbitAssert hasQueue(String queueName, QueueMatcher... matchers){

        return hasQueue("/", queueName, matchers);
    }

    /**
     * Assert that RabbitMQ has the specified queue.
     * @param vhost Name of the vhost with the queue.
     * @param queueName Name of the Queue.
     * @param matchers Modifies the assertion by adding criteria to match.
     * @return this.
     */
    public RabbitAssert hasQueue(String vhost, String queueName, QueueMatcher... matchers) {

        Optional<Queue> queue = mgmt.queues().get(vhost, queueName);

        assertTrue(
                String.format("Queue '%s' does not exist and should on vhost '%s'.", queueName, vhost), queue.isPresent());

        if (matchers != null && matchers.length > 0) {

            MatchResult result = isMatch(queue.get(), matchers);

            assertTrue(result.getReason(), result.isMatch());
        }

        return this;
    }

    /**
     * Assert that RabbitMQ does not have the queue specified on the default virtual host.
     * @param queueName Name of the Queue that should not exist.
     * @return this.
     */
    public RabbitAssert doesNotHaveQueue(String queueName){

        return doesNotHaveQueue("/", queueName);
    }

    /**
     * Assert that RabbitMQ does not have the queue specified on the default virtual host.
     * @param vhost Name of the vhost with the queue.
     * @param queueName Name of the Queue that should not exist.
     * @return this.
     */
    public RabbitAssert doesNotHaveQueue(String vhost, String queueName) {

        Optional<Queue> queue = mgmt.queues().get(vhost, queueName);

        assertFalse(
                String.format("Queue '%s' does not exist and should on vhost '%s'.", queueName, vhost), queue.isPresent());

        return this;
    }

    /**
     * Assert that the specified binding exists between an Exchange and a Queue.
     * @param exchange Name of the source exchange.
     * @param queue Name of the destination queue.
     * @param matchers Modifies the assertion by adding criteria to match.
     * @return this.
     */
    public RabbitAssert hasEtoQBinding(String exchange, String queue, BindingMatcher... matchers) {

        return hasEtoQBinding("/", exchange, queue, matchers);
    }

    /**
     * Assert that the specified binding exists between an Exchange and a Queue.
     * @param vhost Name of the vhost with the binding.
     * @param exchange Name of the source exchange.
     * @param queue Name of the destination queue.
     * @param matchers Modifies the assertion by adding criteria to match.
     * @return this.
     */
    public RabbitAssert hasEtoQBinding(String vhost, String exchange, String queue, BindingMatcher... matchers) {

        Optional<Collection<Binding>> bindings = mgmt.bindings().getEtoQ(vhost, exchange, queue);

        assertTrue(
                String.format("No E->Q binding found for '%s' and '%s' on vhost '%s'", exchange, queue, vhost),
                bindings.isPresent());

        assertTrue(
                String.format("No E->Q binding found for '%s' and '%s' on vhost '%s'",
                    exchange, queue, vhost),
                bindings.get().size() > 0);

        if (matchers != null && matchers.length > 0) {

            MatchResult result = hasItemThatMatches(bindings.get(), matchers);

            assertTrue(result.getReason(), result.isMatch());
        }

        return this;
    }

    /**
     * Assert that the specified binding exists between an Exchange and another Exchange.
     * @param sourceExchange Name of the source exchange.
     * @param destinationExchange Name of the destination exchange.
     * @param matchers Modifies the assertion by adding criteria to match.
     * @return this.
     */
    public RabbitAssert hasEtoEBinding(String sourceExchange, String destinationExchange, BindingMatcher... matchers) {

        return hasEtoQBinding("/", sourceExchange, destinationExchange, matchers);
    }

    /**
     * Assert that the specified binding exists between an Exchange and another Exchange.
     * @param vhost Name of the vhost with the binding.
     * @param sourceExchange Name of the source exchange.
     * @param destinationExchange Name of the destination exchange.
     * @param matchers Modifies the assertion by adding criteria to match.
     * @return this.
     */
    public RabbitAssert hasEtoEBinding(String vhost, String sourceExchange, String destinationExchange, BindingMatcher... matchers) {

        Optional<Collection<Binding>> bindings = mgmt.bindings().getEtoE(vhost, sourceExchange, destinationExchange);

        assertTrue(
                String.format("No E->E binding found for '%s' and '%s' on vhost '%s'",
                    sourceExchange, destinationExchange, vhost),
                bindings.isPresent());

        assertTrue(
                String.format("No E->E binding found for '%s' and '%s' on vhost '%s'",
                    sourceExchange, destinationExchange, vhost),
                bindings.get().size() > 0);

        if (matchers != null && matchers.length > 0) {

            MatchResult result = hasItemThatMatches(bindings.get(), matchers);

            assertTrue(result.getReason(), result.isMatch());
        }

        return this;
    }

    /**
     * Assert that RabbitMQ does not have a binding for the specified exchange and queue.
     * @param exchange Name of the source exchange.
     * @param queue Name of the destination queue.
     * @param matchers Modifies the assertion by adding criteria to match.
     * @return this.
     */
    public RabbitAssert doesNotHaveEtoQBinding(String exchange, String queue, BindingMatcher... matchers) {

        return doesNotHaveEtoQBinding("/", exchange, queue, matchers);
    }

    /**
     * Assert that RabbitMQ does not have a binding for the specified exchange and queue.
     * @param vhost Name of the vhost with the binding.
     * @param exchange Name of the source exchange.
     * @param queue Name of the destination queue.
     * @param matchers Modifies the assertion by adding criteria to match.
     * @return this.
     */
    public RabbitAssert doesNotHaveEtoQBinding(String vhost, String exchange, String queue, BindingMatcher... matchers) {

        Optional<Collection<Binding>> bindings = mgmt.bindings().getEtoQ(vhost, exchange, queue);

        if (bindings.isPresent() && bindings.get().size() > 0) {

            if (matchers != null && matchers.length > 0) {

                MatchResult result = doesNotHaveItemThatMatches(bindings.get(), matchers);

                assertFalse(result.getReason(), result.isMatch());
            }
            else {

                fail(String.format("E->Q binding found for '%s' and '%s' on vhost '%s' that should not exist.",
                        exchange, queue, vhost));
            }
        }

        return this;
    }

    /**
     * Assert that RabbitMQ does not have a binding for the specified exchange and exchange.
     * @param sourceExchange Name of the source exchange.
     * @param destinationExchange Name of the destination exchange..
     * @param matchers Modifies the assertion by adding criteria to match.
     * @return this.
     */
    public RabbitAssert doesNotHaveBindingEtoE(
            String sourceExchange, String destinationExchange, BindingMatcher... matchers) {

        return doesNotHaveBindingEtoE("/", sourceExchange, destinationExchange, matchers);
    }

    /**
     * Assert that RabbitMQ does not have a binding for the specified exchange and exchange.
     * @param vhost Name of the vhost with the binding.
     * @param sourceExchange Name of the source exchange.
     * @param destinationExchange Name of the destination exchange..
     * @param matchers Modifies the assertion by adding criteria to match.
     * @return this.
     */
    public RabbitAssert doesNotHaveBindingEtoE(
            String vhost, String sourceExchange, String destinationExchange, BindingMatcher... matchers) {

        Optional<Collection<Binding>> bindings = mgmt.bindings().getEtoE(vhost, sourceExchange, destinationExchange);

        if (bindings.isPresent() && bindings.get().size() > 0) {

            if (matchers != null && matchers.length > 0) {

                MatchResult result = doesNotHaveItemThatMatches(bindings.get(), matchers);

                assertFalse(result.getReason(), result.isMatch());
            }
            else {

                fail(String.format("E->E binding found for '%s' and '%s' on vhost '%s' that should not exist.",
                        sourceExchange, destinationExchange, vhost));
            }
        }

        return this;
    }

    /**
     * Assert that the specified Queue has a message matching the supplied matcher query.
     * @param queueName Name of the Queue.
     * @param matchers Criteria for matching the message.
     * @return this.
     */
    public RabbitAssert hasMessage(String queueName, MessageMatcher... matchers){

        return hasMessage("/", queueName, matchers);
    }

    /**
     * Assert that the specified Queue has a message matching the supplied matcher query.
     * @param queue Queue.
     * @param matchers Criteria for matching the message.
     * @return this.
     */
    public RabbitAssert hasMessage(Queue queue, MessageMatcher... matchers){

        return hasMessage(queue.getVhost(), queue.getName(), matchers);
    }

    /**
     * Assert that the specified Queue has a message matching the supplied criteria.
     * @param vhost Name of the vhost with the queue.
     * @param queueName Name of the Queue.
     * @param matchers Criteria for matching the message.
     * @return this.
     */
    public RabbitAssert hasMessage(String vhost, String queueName, MessageMatcher... matchers){

        Preconditions.checkNotNull(matchers);

        Optional<Collection<ReceivedMessage>> messages =
                mgmt.queues().consume(vhost, queueName, ConsumeOptions.builder().retrieveAtMost(100).build());

        assertTrue(
                String.format("Queue '%s' on vhost '%s' does not have any messages.", queueName, vhost),
                messages.isPresent());

        assertTrue(
                String.format("Queue '%s' on vhost '%s' does not have any messages.", queueName, vhost),
                messages.get().size() > 0);

        MatchResult result = hasItemThatMatches(messages.get(), matchers);

        assertTrue(result.getReason(), result.isMatch());

        return this;
    }

    /**
     * Assert that the specified Queue does not have the message matching the supplied criteria.
     * @param queue Queue.
     * @param matchers Criteria for matching the message.
     * @return this.
     */
    public RabbitAssert doesNotHaveMessage(Queue queue, MessageMatcher... matchers){

        return doesNotHaveMessage(queue.getVhost(), queue.getName(), matchers);
    }

    /**
     * Assert that the specified Queue does not have the message matching the supplied criteria.
     * @param queueName Name of the Queue..
     * @param matchers Criteria for matching the message.
     * @return this.
     */
    public RabbitAssert doesNotHaveMessage(String queueName, MessageMatcher... matchers){

        return doesNotHaveMessage("/", queueName, matchers);
    }

    /**
     * Assert that the specified Queue does not have the message matching the supplied criteria.
     * @param vhost Name of the vhost with the queue.
     * @param queueName Name of the Queue.
     * @param matchers Criteria for matching the message.
     * @return this.
     */
    public RabbitAssert doesNotHaveMessage(String vhost, String queueName, MessageMatcher... matchers){

        Preconditions.checkNotNull(matchers);

        Optional<Collection<ReceivedMessage>> messages =
                mgmt.queues().consume(vhost, queueName, ConsumeOptions.builder().retrieveAtMost(100).build());

        if (messages.isPresent() && messages.get().size() > 0) {

            MatchResult result = doesNotHaveItemThatMatches(messages.get(), matchers);

            assertFalse(result.getReason(), result.isMatch());
        }

        return this;
    }

    /**
     * Assert that the target broker/cluster has a federation link that matches the supplied criteria.
     * @param matchers Criteria for matching the link.
     * @return this.
     */
    public RabbitAssert hasFederationLink(FederationLinkMatcher... matchers){

        Preconditions.checkNotNull(matchers);

        Collection<FederationLink> links = mgmt.federation().links();

        MatchResult result = hasItemThatMatches(links, matchers);

        assertTrue(result.getReason(), result.isMatch());

        return this;
    }
    
    /**
     sert that the target broker/cluster does not have a federation link that matches the supplied criteria.
     * @param matchers Criteria for matching the link.
     * @return this.
     */
    public RabbitAssert doesNotHaveFederationLink(FederationLinkMatcher... matchers){

        Preconditions.checkNotNull(matchers);

        Collection<FederationLink> links = mgmt.federation().links();

        MatchResult result = doesNotHaveItemThatMatches(links, matchers);

        assertFalse(result.getReason(), result.isMatch());

        return this;
    }

    /**
     * Verify the delivery of a message (or non-delivery) on multiple queues.
     * @return A DeliveryVerification fluent interface.
     */
    public DeliveryVerification verifyDelivery(){

        return new DeliveryVerification(null);
    }

    /**
     * Verify the delivery of a message (or non-delivery) on multiple queues.
     * @param matchers Criteria for match the message.
     * @return A DeliveryVerification fluent interface.
     */
    public DeliveryVerification verifyDelivery(MessageMatcher... matchers){

        return new DeliveryVerification(matchers);
    }

    /**
     * Given a collection of items and criteria for matching, determine if there is a match, and
     * if there isn't, explain why.
     * @param items Items to interrogate.
     * @param matchers Criteria for matching.
     * @param <T> Type to match.
     * @return MatchResult with the aggregated results of not matching.
     */
    static <T> MatchResult hasItemThatMatches(Collection<T> items, Matcher<T>[] matchers){

        Preconditions.checkNotNull(items);
        Preconditions.checkNotNull(matchers);

        List<String> reasons = Lists.newArrayList();

        for (T item : items){

            MatchResult result = isMatch(item, matchers);

            if (result.isMatch){

                return result;
            }
            else {

                reasons.add(result.getReason());
            }
        }

        return MatchResult.doesNotMatch(formatReasons(reasons));
    }

    /**
     * Given a collection of items and criteria for matching, determine if there is a not a match, and
     * if there is, explain why.
     * @param items Items to interrogate.
     * @param matchers Criteria for matching.
     * @param <T> Type to match.
     * @return MatchResult with the aggregated results of matching.
     */
    static <T> MatchResult doesNotHaveItemThatMatches(Collection<T> items, Matcher<T>[] matchers){

        Preconditions.checkNotNull(items);
        Preconditions.checkNotNull(matchers);

        List<String> reasons = Lists.newArrayList();

        for (T item : items){

            MatchResult result = doesNotMatch(item, matchers);

            if (result.isMatch()){

                reasons.add(result.getReason());
            }
        }

        if (reasons.size() == 0) return MatchResult.doesNotMatch();

        return MatchResult.hasMatch(formatReasons(reasons));
    }

    /**
     * Match strategy assumes ALL matchers MATCH in order for MatchResult.isMatch() to be true.
     * @param item Item to interrogate.
     * @param matchers Array of matchers that perform the evaluation.
     * @param <T> Type of the Item.
     * @return The results of the interrogation.
     */
    static <T> MatchResult isMatch(T item, Matcher<T>[] matchers){

        Preconditions.checkNotNull(item);
        Preconditions.checkNotNull(matchers);

        for(Matcher<T> matcher : matchers){

            if (!matcher.matches(item)) {

                return MatchResult.doesNotMatch(matcher.getNotMatchReason(item));
            }
        }

        return MatchResult.hasMatch();
    }

    /**
     * Match strategy assumes that if ANY matchers DO NOT MATCH, MatchResult.isMatch() will be false.
     * @param item Item to interrogate.
     * @param matchers Array of matchers that perform the evaluation.
     * @param <T> Type of the Item.
     * @return The results of the interrogation.
     */
    static <T> MatchResult doesNotMatch(T item, Matcher<T>[] matchers){

        Preconditions.checkNotNull(item);
        Preconditions.checkNotNull(matchers);

        List<String> matchReasons = Lists.newArrayList();

        for(Matcher<T> matcher : matchers){

            if (matcher.matches(item)){

                matchReasons.add(matcher.getMatchReason(item));
            }
            else {

                return MatchResult.doesNotMatch();
            }
        }
        return MatchResult.hasMatch(formatReasons(matchReasons));
    }

    /**
     * Utility for formatting a set of reasons for match success or failure.
     * @param reasons Collection of reasons.
     * @return Formatted string of reasons.
     */
    static String formatReasons(Collection<String> reasons){

        return String.format("[ %s ]", Joiner.on(" | ").join(reasons));
    }

    /**
     * Splits a CSV string used by RabbitMQ for User "tags" into a collection of individual string tags.
     * @param tagCsv Tag string, comma separated.
     * @return List of string tags.
     */
    static List<String> splitTags(String tagCsv){

        Iterable<String> tags = Splitter.on(",").trimResults().split(tagCsv);

        ArrayList<String> tagList = Lists.newArrayList();

        Iterables.addAll(tagList, tags);

        return tagList;
    }

    public static class MatchResult {

        String reason;

        boolean isMatch = false;

        public MatchResult(String reason, boolean isMatch) {
            this.reason = reason;
            this.isMatch = isMatch;
        }

        public String getReason() {
            return reason;
        }

        public boolean isMatch() {
            return isMatch;
        }

        public static MatchResult hasMatch(){

            return new MatchResult(null, true);
        }

        public static MatchResult hasMatch(String reason){

            return new MatchResult(reason, true);
        }

        public static MatchResult doesNotMatch(){

            return new MatchResult(null, false);
        }

        public static MatchResult doesNotMatch(String reason){

            return new MatchResult(reason, false);
        }
    }

    public class DeliveryVerification {

        MessageMatcher[] matchers;

        List<Queue> shouldHaveMessage = Lists.newArrayList();

        List<Queue> shouldNotHaveMessage = Lists.newArrayList();

        public DeliveryVerification(MessageMatcher[] matchers){

            this.matchers = matchers;
        }

        public DeliveryVerification on(String... queues){

            for (String queueName : queues){

                shouldHaveMessage.add(new Queue(queueName));
            }

            return this;
        }

        public DeliveryVerification on(Queue... queues){

            shouldHaveMessage.addAll(Arrays.asList(queues));

            return this;
        }

        public DeliveryVerification butNotOn(Queue... queues){

            return notOn(queues);
        }

        public DeliveryVerification butNotOn(String... queues){

            return notOn(queues);
        }

        public DeliveryVerification notOn(Queue... queues){

            shouldNotHaveMessage.addAll(Arrays.asList(queues));

            return this;
        }

        public DeliveryVerification notOn(String... queues){

            for (String queueName : queues){

                shouldNotHaveMessage.add(new Queue(queueName));
            }

            return this;
        }

        public void deliver(String exchangeName, Message message){

            deliver("/", exchangeName, message);
        }

        public void deliver(String vhost, String exchangeName, Message message){

            // If matchers weren't supplied, we'll use the default equivalence test.
            checkMatchers(message);

            mgmt.exchanges().publish(vhost, exchangeName, message);

            makeAssertions();
        }

        private void checkMatchers(Message message){

            if (matchers == null || matchers.length == 0)
                matchers = new MessageMatcher[]{ MessageMatchers.marked(message) };
        }

        private void makeAssertions() {

            for (Queue q : shouldHaveMessage){

                hasMessage(q, matchers);
            }

            for (Queue q : shouldNotHaveMessage){

                doesNotHaveMessage(q, matchers);
            }
        }
    }
}
