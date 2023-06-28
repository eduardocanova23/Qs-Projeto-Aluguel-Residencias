package com.qos.fakebnb.service.mapper;

import com.qos.fakebnb.domain.*;
import com.qos.fakebnb.service.dto.RentalPlanDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link RentalPlan} and its DTO {@link RentalPlanDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface RentalPlanMapper extends EntityMapper<RentalPlanDTO, RentalPlan> {}
