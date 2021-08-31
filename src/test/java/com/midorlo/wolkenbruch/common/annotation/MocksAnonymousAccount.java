//package com.midorlo.wolkenbruch.common.annotation;
//
//import org.springframework.security.core.context.SecurityContext;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.test.context.support.WithSecurityContext;
//import org.springframework.security.test.context.support.WithSecurityContextFactory;
//
//import java.lang.annotation.ElementType;
//import java.lang.annotation.Retention;
//import java.lang.annotation.RetentionPolicy;
//import java.lang.annotation.Target;
//
///**
// * <h2>Configure MockMVC to have no authentication.</h2>
// * <h3>Setup for @{@link IntegrationTest}.</h3>
// * Facade for @{@link WithSecurityContext}(factory = MocksAnonymousAccount.Factory.class)
// */
//@Target({
//        ElementType.METHOD,
//        ElementType.TYPE
//})
//@WithSecurityContext(
//        factory = MocksAnonymousAccount.Factory.class
//)
//@Retention(RetentionPolicy.RUNTIME)
//public @interface MocksAnonymousAccount {
//
//    /**
//     * See {@link WithSecurityContextFactory}<{@link MocksAnonymousAccount}>
//     */
//    class Factory implements WithSecurityContextFactory<MocksAnonymousAccount> {
//
//        /**
//         * @param annotation see {@link MocksAnonymousAccount}
//         * @return see {@link SecurityContext}
//         */
//        @Override
//        public SecurityContext createSecurityContext(MocksAnonymousAccount annotation) {
//            return SecurityContextHolder.createEmptyContext();
//        }
//    }
//}
//
