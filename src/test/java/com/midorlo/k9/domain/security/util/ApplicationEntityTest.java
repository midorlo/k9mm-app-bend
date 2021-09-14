//package com.midorlo.k9.domain.security.util;
//
//import com.midorlo.k9.common.TestUtils;
//import com.midorlo.k9.domain.AbstractK9Entity;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//class ApplicationEntityTest implements IEntityTest {
//
//   public static final class ApplicationEntityTestImplAbstract extends AbstractK9Entity {
//      public ApplicationEntityTestImplAbstract() {
//      }
//   }
//
//   private ApplicationEntityTestImplAbstract e1, e2;
//
//   @BeforeEach
//   public void setup() {
//      e1 = new ApplicationEntityTestImplAbstract();
//      e1.setId(1L);
//      e1.setName("1");
//      e2 = new ApplicationEntityTestImplAbstract();
//      e2.setId(1L);
//      e2.setName("1");
//   }
//
//   @Test
//   public void jpaSpecificEqualsValid() throws Exception {
//      TestUtils.jpaEqualsVerifier(ApplicationEntityTestImplAbstract.class);
//   }
//
//   @Test
//   public void equalsValid() {
//      assertThat(e1).isEqualTo(e2);
//   }
//
//   @Test
//   public void nameNotEqual() {
//      e1.setName("2");
//      assertThat(e1).isNotEqualTo(e2);
//   }
//
//   @Test
//   public void nameNull() {
//      e1.setName(null);
//      assertThat(e1).isNotEqualTo(e2);
//   }
//
//   @Test
//   public void idNotEqual() {
//      e1.setId(2L);
//      assertThat(e1).isNotEqualTo(e2);
//   }
//
//   @Test
//   public void idNull() {
//      e1.setId(null);
//      assertThat(e1).isNotEqualTo(e2);
//   }
//}
