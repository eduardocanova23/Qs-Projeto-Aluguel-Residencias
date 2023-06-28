package com.qos.fakebnb.service.mapper;

import com.qos.fakebnb.domain.*;
import com.qos.fakebnb.service.dto.PaymentProcessDTO;
import org.akip.service.mapper.ProcessInstanceMapper;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link PaymentProcess} and its DTO {@link PaymentProcessDTO}.
 */
@Mapper(componentModel = "spring", uses = { ProcessInstanceMapper.class, PaymentMapper.class })
public interface PaymentProcessMapper extends EntityMapper<PaymentProcessDTO, PaymentProcess> {
    @Mapping(target = "processInstance", source = "processInstance")
    @Mapping(target = "payment", source = "payment")
    PaymentProcessDTO toDto(PaymentProcess s);
}
