package io.pivotal.rmqestimator.matchers;

/**
 * I admit this interface is a little obtuse, but hopefully you'll understand the rationale:
 *
 * 1.  A resulting match is not necessarily the overall objective.  For instance, matchers are used
 *     to assert non-matches.
 * 2.  Because of the duality of matcher (goal either being a match or non-match) we need to know why
 *     a match or non-match occurred.  Instead of over complicating the external API by introducing
 *     a result object, I've elected to add two methods to this interface, that when asked, will
 *     tell you why or why not a match has occurred.
 * 3.  The reason methods are only used to provide clarity to a failing JUnit assertion.
 *
 * @author Richard Clayton (Berico Technologies)
 */
public interface Matcher<T> {

    /**
     * Does the item meet the matcher's criteria?
     * @param item Item to test.
     * @return TRUE if it does meet the criteria.
     */
    boolean matches(T item);

    /**
     * Assuming that "matchers(item) == false", tell us why it was false (e.g. not same exchange name).
     * @param item Item that did not match.
     * @return Reason why the item did not meet the criteria.
     */
    String getNotMatchReason(T item);

    /**
     * Assuming that "matches(item) == true", tell us why it was true (e.g. same exchange name).
     * @param item Item that matched.
     * @return Reason why the item met the criteria.
     */
    String getMatchReason(T item);
}
