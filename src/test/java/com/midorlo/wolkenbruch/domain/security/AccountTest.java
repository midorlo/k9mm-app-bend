//package com.midorlo.k9.domain.security;
//
//import com.midorlo.k9.common.TestUtils;
//import com.midorlo.k9.common.annotation.UnitTest;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//
//@UnitTest
//public class AccountTest {
//
//    private Account e1, e2;
//
//    @BeforeEach
//    public void setup() {
//        e1 = new Account();
//        e1.setId(1L);
//        e1.setName("1");
//        e2 = new Account();
//        e2.setId(1L);
//        e2.setName("1");
//    }
//
//    @Test
//    public void jpaSpecificEqualsValid() throws Exception {
//        TestUtils.jpaEqualsVerifier(Account.class);
//    }
//
//    @Test
//    public void equalsValid() {
//        assertThat(e1).isEqualTo(e2);
//    }
//
//    @Test
//    public void nameNotEqual() {
//        e1.setName("2");
//        assertThat(e1).isNotEqualTo(e2);
//    }
//}
