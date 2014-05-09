package com.fleurida.blueprint;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Target;

/**
 * 
 * Just used for testing
 * 
 */
@Retention(RUNTIME)
@Documented
@Target(TYPE)
public @interface TestAnnotation {

}
