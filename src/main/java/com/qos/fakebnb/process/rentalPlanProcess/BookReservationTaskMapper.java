package com.qos.fakebnb.process.rentalPlanProcess;

import com.qos.fakebnb.domain.RentalPlan;
import com.qos.fakebnb.domain.RentalPlanProcess;
import com.qos.fakebnb.service.dto.RentalPlanDTO;
import com.qos.fakebnb.service.dto.RentalPlanProcessDTO;
import org.akip.service.mapper.ProcessInstanceMapper;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = { ProcessInstanceMapper.class })
public interface BookReservationTaskMapper {
    @Mapping(target = "processInstance", source = "processInstance", qualifiedByName = "loadTaskContext")
    RentalPlanProcessDTO toRentalPlanProcessDTO(RentalPlanProcess rentalPlanProcess);

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "city", source = "city")
    @Mapping(target = "neighborhood", source = "neighborhood")
    @Mapping(target = "startDate", source = "startDate")
    @Mapping(target = "endDate", source = "endDate")
    @Mapping(target = "confirmation", source = "confirmation")
    RentalPlanDTO toRentalPlanDTO(RentalPlan rentalPlan);
}
