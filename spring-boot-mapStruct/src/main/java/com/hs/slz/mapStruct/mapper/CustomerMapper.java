package com.hs.slz.mapStruct.mapper;

import com.hs.slz.mapStruct.entity.BooleanStrFormat;
import com.hs.slz.mapStruct.entity.Customer;
import com.hs.slz.mapStruct.entity.CustomerDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {BooleanStrFormat.class})
public interface CustomerMapper {

    CustomerMapper INSTANCES = Mappers.getMapper(CustomerMapper.class);

    @Mappings({
            @Mapping(source = "name", target = "customerName"),
            @Mapping(source = "isDisable", target = "disable")
    })
    CustomerDto toCustomerDto(Customer customer);
}
