package test.hs.slz.mapStruct;

import cn.hutool.json.JSONUtil;
import com.hs.slz.mapStruct.entity.*;
import com.hs.slz.mapStruct.mapper.CustomerMapper;
import com.hs.slz.mapStruct.mapper.ItemConverter;
import com.hs.slz.mapStruct.mapper.PersonConverter;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

//@SpringBootTest(classes = ApplicationStarter.class)
//@RunWith(SpringRunner.class)
public class PersonConverterTest {

    @Test
    public void testOne2Many() {
        Person person = new Person(1L, "zhige   ", "zhige.me@gmail.com", new Date(), new User(1));
        PersonDTO personDTO = PersonConverter.INSTANCE.toDTO(person);
        assertNotNull(personDTO);
        assertEquals(personDTO.getId(), person.getId());
        assertEquals(personDTO.getName(), StringUtils.trim(person.getName()));
        assertEquals(personDTO.getBirth(), person.getBirthday());
        assertNull(personDTO.getEmail());
        String format = DateFormatUtils.format(personDTO.getBirth(), "yyyy-MM-dd HH:mm:ss");
        assertEquals(personDTO.getBirthDateFormat(), format);
        assertEquals(personDTO.getBirthExpressionFormat(), format);

        List<Person> people = new ArrayList<>();
        people.add(person);
        List<PersonDTO> personDTOs = PersonConverter.INSTANCE.toDTO(people);
        assertNotNull(personDTOs);
    }

//    @Autowired
//    private ItemConverter itemConverter; //@Mapper(componentModel="spring")

    @Test
    public void many2One() {
        Item item = new Item(1L, "iPhone X");
        Sku sku = new Sku(2L, "phone12345", 1000000);
        SkuDTO skuDTO = ItemConverter.INSTANCE.domain2dto(item, sku);
        assertNotNull(skuDTO);
        assertEquals(skuDTO.getSkuId(), sku.getId());
        assertEquals(skuDTO.getSkuCode(), sku.getCode());
        assertEquals(skuDTO.getSkuPrice(), sku.getPrice());
        assertEquals(skuDTO.getItemId(), item.getId());
        assertEquals(skuDTO.getItemName(), item.getTitle());
    }

    @Test
    public void testUpdate() {
        Person person = new Person(1L, "zhige", "zhige.me@gmail.com", new Date(), new User(1));
        PersonDTO personDTO = PersonConverter.INSTANCE.toDTO(person);
        assertEquals("zhige", personDTO.getName());
        person.setName("xiaozhi");
        PersonConverter.INSTANCE.update(person, personDTO);
        assertEquals("xiaozhi", personDTO.getName());
    }

    @Test
    public void toEntity() {
        Person person = new Person(1L, "zhige", "zhige.me@gmail.com", new Date(), new User(1));
        PersonDTO personDTO = PersonConverter.INSTANCE.toDTO(person);
        Person anotherPerson = PersonConverter.INSTANCE.fromDTO(personDTO);
        System.out.println(JSONUtil.toJsonStr(anotherPerson));
    }

    @Test
    public void testCustomizeMapperRule() {
        Customer customer = new Customer(1L, "张三", true);
        CustomerDto customerDto = CustomerMapper.INSTANCES.toCustomerDto(customer);
        assertEquals(customer.getId(), customerDto.getId());
        assertEquals(customer.getName(), customerDto.getCustomerName());
        assertEquals(customer.getIsDisable(), customerDto.getDisable().equals("Y"));
    }
}