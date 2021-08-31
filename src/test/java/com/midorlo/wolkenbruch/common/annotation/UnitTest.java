package com.midorlo.wolkenbruch.common.annotation;

import com.midorlo.wolkenbruch.BendApplication;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;


/**
 * <h2>Declares a unit Test.</h2>
 * Inherits @{@link SpringBootTest} with (classes = {@link BendApplication}.class)
 */
@Target(ElementType.TYPE)
@SpringBootTest(classes = BendApplication.class)
public @interface UnitTest {}
