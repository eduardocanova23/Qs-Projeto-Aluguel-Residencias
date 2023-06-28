package com.qos.fakebnb.process.paymentProcess;

import com.qos.fakebnb.domain.Payment;
import com.qos.fakebnb.domain.PaymentProcess;
import com.qos.fakebnb.service.dto.PaymentDTO;
import com.qos.fakebnb.service.dto.PaymentProcessDTO;
import org.akip.service.mapper.ProcessInstanceMapper;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = { ProcessInstanceMapper.class })
public interface PaymentDetailsTaskMapper {
    @Mapping(target = "processInstance", source = "processInstance", qualifiedByName = "loadTaskContext")
    PaymentProcessDTO toPaymentProcessDTO(PaymentProcess paymentProcess);

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "cardNumber", source = "cardNumber")
    @Mapping(target = "CVV", source = "CVV")
    @Mapping(target = "expirationDate", source = "expirationDate")
    @Mapping(target = "phoneNumber", source = "phoneNumber")
    PaymentDTO toPaymentDTO(Payment payment);
}
