package io.pivotal.rmqestimator.rest.mgmt.model;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Indicates that a property is returned by RabbitMQ Management Console and should not be set or expected when
 * you instantiate an object manually.
 *
 * @author Richard Clayton (Berico Technologies)
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD})
public @interface ProducedByRabbit {}
