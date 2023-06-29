package com.qos.fakebnb.process.rentalPlanProcess;

import com.qos.fakebnb.domain.LivingQuarters;
import com.qos.fakebnb.domain.RentalPlan;
import com.qos.fakebnb.domain.RentalPlanProcess;
import com.qos.fakebnb.service.dto.LivingQuartersDTO;
import com.qos.fakebnb.service.dto.RentalPlanDTO;
import com.qos.fakebnb.service.dto.RentalPlanProcessDTO;
import org.akip.service.mapper.ProcessInstanceMapper;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = { ProcessInstanceMapper.class })
public interface TaskBrowseResidencesMapper {
    @Mapping(target = "processInstance", source = "processInstance", qualifiedByName = "loadTaskContext")
    RentalPlanProcessDTO toRentalPlanProcessDTO(RentalPlanProcess rentalPlanProcess);

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "city", source = "city")
    @Mapping(target = "neighborhood", source = "neighborhood")
    @Mapping(target = "userName", source = "userName")
    @Mapping(target = "userEmail", source = "userEmail")
    @Mapping(target = "startDate", source = "startDate")
    @Mapping(target = "endDate", source = "endDate")
    @Mapping(target = "rentalConfirmationNumber", source = "rentalConfirmationNumber")
    @Mapping(target = "livingQuarters", source = "livingQuarters")
    RentalPlanDTO toRentalPlanDTO(RentalPlan rentalPlan);

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    LivingQuartersDTO toLivingQuartersDTO(LivingQuarters name);
}
