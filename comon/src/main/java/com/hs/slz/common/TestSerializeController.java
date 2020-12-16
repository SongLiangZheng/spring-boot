package com.hs.slz.common;

import cn.hutool.json.JSONUtil;
import com.hs.slz.common.dto.Bond;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class TestSerializeController {


    @GetMapping("/serial")
    public Bond serial() {
        Bond bond = new Bond();
        bond.setName("11");
        bond.setCreateTime(new Date());
        bond.setModDate(new Date());
        return bond;
    }

    @PostMapping("/deSerial")
    public void deSerial(@RequestBody Bond bond) {
        System.out.println(JSONUtil.toJsonStr(bond));
    }

}
