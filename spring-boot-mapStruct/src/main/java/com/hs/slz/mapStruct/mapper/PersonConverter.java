package com.hs.slz.mapStruct.mapper;

import com.hs.slz.mapStruct.entity.Person;
import com.hs.slz.mapStruct.entity.PersonDTO;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PersonConverter {
    PersonConverter INSTANCE = Mappers.getMapper(PersonConverter.class);
    @Mappings({
            @Mapping(source = "birthday", target = "birth"),
            @Mapping(source = "birthday", target = "birthDateFormat", dateFormat = "yyyy-MM-dd HH:mm:ss"),
            @Mapping(target = "birthExpressionFormat", expression = "java(org.apache.commons.lang3.time.DateFormatUtils.format(person.getBirthday(),\"yyyy-MM-dd HH:mm:ss\"))"),
            @Mapping(source = "user.age", target = "age"),
            @Mapping(target = "email", ignore = true)
    })
    PersonDTO toDTO(Person person);

    @Mappings({
            @Mapping(source = "birth", target = "birthday"),
            @Mapping(source = "age", target = "user.age"),
            @Mapping(target = "email")
    })
    Person fromDTO(PersonDTO personDTO);

    @InheritConfiguration(name = "toDTO")
    void update(Person person, @MappingTarget PersonDTO personDTO);

    List<PersonDTO> toDTO(List<Person> people);


    default Integer convert2Int(Boolean value) {
        return 0;
    }


}
