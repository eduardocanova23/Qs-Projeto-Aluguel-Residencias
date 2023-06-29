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
public interface PaymentDetailsTaskMapper {
    @Mapping(target = "processInstance", source = "processInstance", qualifiedByName = "loadTaskContext")
    RentalPlanProcessDTO toRentalPlanProcessDTO(RentalPlanProcess rentalPlanProcess);

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "userName", source = "userName")
    @Mapping(target = "cardNumber", source = "cardNumber")
    @Mapping(target = "cardVerificationValue", source = "cardVerificationValue")
    @Mapping(target = "expirationDate", source = "expirationDate")
    RentalPlanDTO toRentalPlanDTO(RentalPlan rentalPlan);
}
