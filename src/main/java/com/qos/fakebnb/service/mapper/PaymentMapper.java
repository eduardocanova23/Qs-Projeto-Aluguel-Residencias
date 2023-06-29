package com.qos.fakebnb.service.mapper;

import com.qos.fakebnb.domain.*;
import com.qos.fakebnb.service.dto.PaymentDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Payment} and its DTO {@link PaymentDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface PaymentMapper extends EntityMapper<PaymentDTO, Payment> {}
