package com.midorlo.k9.domain.security.util;

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
