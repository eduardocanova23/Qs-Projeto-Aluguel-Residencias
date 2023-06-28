package com.qos.fakebnb.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.qos.fakebnb.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class LivingQuartersDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(LivingQuartersDTO.class);
        LivingQuartersDTO livingQuartersDTO1 = new LivingQuartersDTO();
        livingQuartersDTO1.setId(1L);
        LivingQuartersDTO livingQuartersDTO2 = new LivingQuartersDTO();
        assertThat(livingQuartersDTO1).isNotEqualTo(livingQuartersDTO2);
        livingQuartersDTO2.setId(livingQuartersDTO1.getId());
        assertThat(livingQuartersDTO1).isEqualTo(livingQuartersDTO2);
        livingQuartersDTO2.setId(2L);
        assertThat(livingQuartersDTO1).isNotEqualTo(livingQuartersDTO2);
        livingQuartersDTO1.setId(null);
        assertThat(livingQuartersDTO1).isNotEqualTo(livingQuartersDTO2);
    }
}
