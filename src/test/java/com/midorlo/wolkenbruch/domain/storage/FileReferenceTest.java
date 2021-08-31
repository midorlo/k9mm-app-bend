package com.midorlo.wolkenbruch.domain.storage;

import com.midorlo.wolkenbruch.common.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FileReferenceTest {

   private FileReference e1, e2;

   @BeforeEach
   public void setup() {
      e1 = new FileReference();
      e1.setId(1L);
      e1.setName("1");
      e2 = new FileReference();
      e2.setId(1L);
      e2.setName("1");
   }

   @Test
   public void jpaSpecificEqualsValid() throws Exception {
      TestUtils.jpaEqualsVerifier(FileReference.class);
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
}
