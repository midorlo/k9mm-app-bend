package com.midorlo.wolkenbruch.domain.storage;

import com.midorlo.wolkenbruch.common.TestUtils;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FolderReferenceTest {
    @Test
    void equalsVerifier() throws Exception {
        TestUtils.jpaEqualsVerifier(FolderReference.class);
        FolderReference a1 = new FolderReference();
        FolderReference a2 = new FolderReference();
        a1.setId(1L);
        a2.setId(a1.getId());
        assertThat(a1).isEqualTo(a2);
        a2.setId(2L);
        assertThat(a1).isNotEqualTo(a2);
        a1.setId(null);
        assertThat(a1).isNotEqualTo(a2);
    }
}
