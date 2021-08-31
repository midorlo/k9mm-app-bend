package com.midorlo.wolkenbruch.domain.util;

import com.midorlo.wolkenbruch.common.TestUtils;
import com.midorlo.wolkenbruch.domain.ApplicationEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ApplicationEntityTest implements IEntityTest {

   public static final class ApplicationEntityTestImpl extends ApplicationEntity {
      public ApplicationEntityTestImpl() {
      }
   }

   private ApplicationEntityTestImpl e1, e2;

   @BeforeEach
   public void setup() {
      e1 = new ApplicationEntityTestImpl();
      e1.setId(1L);
      e1.setName("1");
      e2 = new ApplicationEntityTestImpl();
      e2.setId(1L);
      e2.setName("1");
   }

   @Test
   public void jpaSpecificEqualsValid() throws Exception {
      TestUtils.jpaEqualsVerifier(ApplicationEntityTestImpl.class);
   }

   @Test
   public void equalsValid() {
      assertThat(e1).isEqualTo(e2);
   }

   @Test
   public void nameNotEqual() {
      e1.setName("2");
      assertThat(e1).isNotEqualTo(e2);
   }

   @Test
   public void nameNull() {
      e1.setName(null);
      assertThat(e1).isNotEqualTo(e2);
   }

   @Test
   public void idNotEqual() {
      e1.setId(2L);
      assertThat(e1).isNotEqualTo(e2);
   }

   @Test
   public void idNull() {
      e1.setId(null);
      assertThat(e1).isNotEqualTo(e2);
   }
}
