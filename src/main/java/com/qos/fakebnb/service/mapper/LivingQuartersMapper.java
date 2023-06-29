package com.qos.fakebnb.service.mapper;

import com.qos.fakebnb.domain.*;
import com.qos.fakebnb.service.dto.LivingQuartersDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link LivingQuarters} and its DTO {@link LivingQuartersDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface LivingQuartersMapper extends EntityMapper<LivingQuartersDTO, LivingQuarters> {
    @Named("name")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    LivingQuartersDTO toDtoName(LivingQuarters livingQuarters);
}
