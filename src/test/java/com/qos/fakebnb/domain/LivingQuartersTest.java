package com.qos.fakebnb.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.qos.fakebnb.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class LivingQuartersTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(LivingQuarters.class);
        LivingQuarters livingQuarters1 = new LivingQuarters();
        livingQuarters1.setId(1L);
        LivingQuarters livingQuarters2 = new LivingQuarters();
        livingQuarters2.setId(livingQuarters1.getId());
        assertThat(livingQuarters1).isEqualTo(livingQuarters2);
        livingQuarters2.setId(2L);
        assertThat(livingQuarters1).isNotEqualTo(livingQuarters2);
        livingQuarters1.setId(null);
        assertThat(livingQuarters1).isNotEqualTo(livingQuarters2);
    }
}
