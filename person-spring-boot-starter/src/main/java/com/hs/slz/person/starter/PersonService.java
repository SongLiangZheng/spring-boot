package com.hs.slz.person.starter;

import cn.hutool.json.JSONUtil;

/**
 * @author zhengsl26931
 */
public class PersonService {

    private PersonProperties personProperties;

    public PersonService() {
    }

    public PersonService(PersonProperties personProperties) {
        this.personProperties = personProperties;
    }

    public String sayHello() {
        return JSONUtil.toJsonStr(personProperties);
    }
}
