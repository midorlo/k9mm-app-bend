package com.midorlo.wolkenbruch.domain.security;

import com.midorlo.wolkenbruch.common.TestUtils;
import com.midorlo.wolkenbruch.common.annotation.UnitTest;
import com.midorlo.wolkenbruch.domain.util.IEntityTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@UnitTest
public class PrivilegeTest implements IEntityTest {

    Privilege e1, e2;

    @BeforeEach
    public void setup() {
        e1 = (Privilege) new Privilege().setId(1L).setName("1");
        e2 = (Privilege) new Privilege().setId(1L).setName("1");
    }

    @Override
    public void jpaSpecificEqualsValid() throws Exception {
        TestUtils.jpaEqualsVerifier(Privilege.class);
    }

    @Test
    void equalsVerifier() throws Exception {
        TestUtils.jpaEqualsVerifier(Privilege.class);
        Privilege a1 = new Privilege("a1");
        Privilege a2 = new Privilege("a1");
        a1.setId(1L);
        a2.setId(a1.getId());
        assertThat(a1).isEqualTo(a2);
        a2.setId(2L);
        assertThat(a1).isNotEqualTo(a2);
        a1.setId(null);
        assertThat(a1).isNotEqualTo(a2);
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
