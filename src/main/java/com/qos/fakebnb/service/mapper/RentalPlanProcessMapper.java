package com.qos.fakebnb.service.mapper;

import com.qos.fakebnb.domain.*;
import com.qos.fakebnb.service.dto.RentalPlanProcessDTO;
import org.akip.service.mapper.ProcessInstanceMapper;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link RentalPlanProcess} and its DTO {@link RentalPlanProcessDTO}.
 */
@Mapper(componentModel = "spring", uses = { ProcessInstanceMapper.class, RentalPlanMapper.class })
public interface RentalPlanProcessMapper extends EntityMapper<RentalPlanProcessDTO, RentalPlanProcess> {
    @Mapping(target = "processInstance", source = "processInstance")
    @Mapping(target = "rentalPlan", source = "rentalPlan")
    RentalPlanProcessDTO toDto(RentalPlanProcess s);
}
