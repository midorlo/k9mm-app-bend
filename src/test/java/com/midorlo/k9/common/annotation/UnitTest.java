package com.midorlo.k9.common.annotation;

import com.midorlo.k9.K9Application;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;


/**
 * <h2>Declares a unit Test.</h2>
 * Inherits @{@link SpringBootTest} with (classes = {@link K9Application}.class)
 */
@Target(ElementType.TYPE)
@SpringBootTest(classes = K9Application.class)
public @interface UnitTest {}
