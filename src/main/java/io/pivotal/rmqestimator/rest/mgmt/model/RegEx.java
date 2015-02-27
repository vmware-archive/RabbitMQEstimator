package io.pivotal.rmqestimator.rest.mgmt.model;

import com.google.common.base.Joiner;

/**
 * @author Richard Clayton (Berico Technologies)
 */
public class RegEx {

    public static String startsWith(String string){

        return String.format("^%s", string);
    }

    public static String endsWith(String string){

        return String.format("%s$", string);
    }

    public static String oneOf(String... strings){

        return String.format("^(?:%s)$", Joiner.on("|").join(strings));
    }

    public static String contains(String string){

        return String.format(".*%s.*", string);
    }

    public static String containsOneOf(String... strings){

        return String.format(".*(?:%s).*", Joiner.on("|").join(strings));
    }
}
