package com.slz.validate.controller;

import com.slz.validate.validate.Create;
import com.slz.validate.validate.Update;
import com.slz.validate.dto.RspDTO;
import com.slz.validate.dto.UserDTO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@RestController
public class UserController {

    @GetMapping("/get")
    public RspDTO<UserDTO> getUser(@RequestParam @NotNull(message = "用户id不能为空") Long userId) {
        return RspDTO.success(null);
    }

    @PostMapping("/insert")
    public RspDTO<UserDTO> insert(@RequestBody @Validated(Create.class) UserDTO userDTO) {
        return RspDTO.success(userDTO);
    }

    @PostMapping("/update/groups")
    public RspDTO<UserDTO> update(@RequestBody @Validated(Update.class) UserDTO userDTO) {
        return RspDTO.success(userDTO);
    }
}
