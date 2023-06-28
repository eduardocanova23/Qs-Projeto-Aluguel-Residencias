package com.qos.fakebnb.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RentalPlanMapperTest {

    private RentalPlanMapper rentalPlanMapper;

    @BeforeEach
    public void setUp() {
        rentalPlanMapper = new RentalPlanMapperImpl();
    }
}
