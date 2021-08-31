package com.midorlo.wolkenbruch.common.annotation;

import com.midorlo.wolkenbruch.BendApplication;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * <h2>Declares an integration Test.</h2>
 * Composite of
 * <ul>
 * <li>@{@link SpringBootTest}</li>
 * <li>@{@link AutoConfigureMockMvc}</li>
 * <li>@{@link ActiveProfiles} (int) <b>only</b> invoke with int-profile present</li>
 * </ul>
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@AutoConfigureMockMvc
@SpringBootTest(classes = BendApplication.class)
@ActiveProfiles("int")
public @interface IntegrationTest {}
