package com.qos.fakebnb.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LivingQuartersMapperTest {

    private LivingQuartersMapper livingQuartersMapper;

    @BeforeEach
    public void setUp() {
        livingQuartersMapper = new LivingQuartersMapperImpl();
    }
}
