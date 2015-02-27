package io.pivotal.rmqestimator;

import org.junit.*;

import io.pivotal.rmqestimator.matchers.RabbitAssert;
import io.pivotal.rmqestimator.rest.mgmt.model.Binding;
import io.pivotal.rmqestimator.rest.mgmt.model.Exchange;
import io.pivotal.rmqestimator.rest.mgmt.model.Message;
import io.pivotal.rmqestimator.rest.mgmt.model.Queue;
import io.pivotal.rmqestimator.service.RabbitNodeMgmtService;
import static io.pivotal.rmqestimator.matchers.BindingMatchers.*;
import static io.pivotal.rmqestimator.matchers.ExchangeMatchers.*;
import static io.pivotal.rmqestimator.matchers.QueueMatchers.*;

/**
 * @author Richard Clayton (Berico Technologies)
 */
//@Ignore("Just an example.  Assumes you have an RabbitMQ Mgmt Console at localhost:15672")
public class TestExample {

    static RabbitNodeMgmtService mgmt;

    static RabbitAssert rabbitAssert;

    @BeforeClass
    public static void setup(){

        mgmt = RabbitNodeMgmtService.builder().build();

        rabbitAssert = new RabbitAssert(mgmt);

        mgmt.exchanges().create(new Exchange("test.example.exchange"));

        mgmt.queues().create(new Queue("test.example.queue"));

        mgmt.queues().create(new Queue("test.example.queue2"));

        mgmt.bindings().create(new Binding("test.example.exchange", "test.example.queue", "test.topic"));

        mgmt.bindings().create(new Binding("test.example.exchange", "test.example.queue2", "test.topic2"));
    }

    @AfterClass
    public static void teardown(){

        mgmt.exchanges().delete("test.example.exchange");

        mgmt.queues().delete("test.example.queue");

        mgmt.queues().delete("test.example.queue2");
    }

    @Test
    public void test_topology_state_and_make_other_assertions(){

        rabbitAssert.hasExchange("test.example.exchange", isDirectType());

        rabbitAssert.hasQueue("test.example.queue", isNotQDurable());

        rabbitAssert.hasEtoQBinding("test.example.exchange", "test.example.queue", routingKey("test.topic"), isExToQ());

        rabbitAssert.verifyDelivery()
                .on("test.example.queue")
                .butNotOn("test.example.queue2")
                .deliver("test.example.exchange",
                        Message.builder()
                                .routingKey("test.topic")
                                .payload("Hello Rabbid Mgmt!")
                                .build());
    }

}
