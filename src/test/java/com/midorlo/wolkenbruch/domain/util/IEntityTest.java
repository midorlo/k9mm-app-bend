package com.midorlo.wolkenbruch.domain.util;

/**
 * Simple contract for entity Tests.
 */
@SuppressWarnings("unused")
public interface IEntityTest {

   void setup();

   void jpaSpecificEqualsValid() throws Exception;

   void equalsValid();

   void nameNotEqual();

   void nameNull();

   void idNotEqual();

   void idNull();
}
