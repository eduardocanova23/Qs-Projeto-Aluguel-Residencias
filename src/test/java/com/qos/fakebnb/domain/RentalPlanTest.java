package com.qos.fakebnb.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.qos.fakebnb.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class RentalPlanTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RentalPlan.class);
        RentalPlan rentalPlan1 = new RentalPlan();
        rentalPlan1.setId(1L);
        RentalPlan rentalPlan2 = new RentalPlan();
        rentalPlan2.setId(rentalPlan1.getId());
        assertThat(rentalPlan1).isEqualTo(rentalPlan2);
        rentalPlan2.setId(2L);
        assertThat(rentalPlan1).isNotEqualTo(rentalPlan2);
        rentalPlan1.setId(null);
        assertThat(rentalPlan1).isNotEqualTo(rentalPlan2);
    }
}
