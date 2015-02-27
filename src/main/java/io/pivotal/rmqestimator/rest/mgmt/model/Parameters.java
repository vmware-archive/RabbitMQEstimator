package io.pivotal.rmqestimator.rest.mgmt.model;

import java.util.Map;

/**
 * @author Richard Clayton (Berico Technologies)
 */
public class Parameters {

    public static class StringParameter extends Parameter<String> {}

    public static class ObjectParameter extends Parameter<Object> {}

    public static class MapParameter extends Parameter<Map<String, Object>> {}
}
