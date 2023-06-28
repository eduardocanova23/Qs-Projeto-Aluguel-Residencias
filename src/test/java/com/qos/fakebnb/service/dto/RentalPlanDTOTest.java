package com.qos.fakebnb.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.qos.fakebnb.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class RentalPlanDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(RentalPlanDTO.class);
        RentalPlanDTO rentalPlanDTO1 = new RentalPlanDTO();
        rentalPlanDTO1.setId(1L);
        RentalPlanDTO rentalPlanDTO2 = new RentalPlanDTO();
        assertThat(rentalPlanDTO1).isNotEqualTo(rentalPlanDTO2);
        rentalPlanDTO2.setId(rentalPlanDTO1.getId());
        assertThat(rentalPlanDTO1).isEqualTo(rentalPlanDTO2);
        rentalPlanDTO2.setId(2L);
        assertThat(rentalPlanDTO1).isNotEqualTo(rentalPlanDTO2);
        rentalPlanDTO1.setId(null);
        assertThat(rentalPlanDTO1).isNotEqualTo(rentalPlanDTO2);
    }
}
