package com.slz.validate.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.slz.validate.validate.Create;
import com.slz.validate.validate.FlagValidator;
import com.slz.validate.validate.IdentityCardNumber;
import com.slz.validate.validate.Update;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class UserDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    /*** 用户ID*/
    @NotNull(message = "用户id不能为空", groups = Update.class)
    private Long userId;

    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空")
    @Length(max = 20, message = "用户名不能超过20个字符", groups = {Create.class, Update.class})
    @Pattern(regexp = "[A-Za-z0-9]+", message = "用户昵称限制：最多20字符，包含文字、字母和数字")
    private String username;

    /**
     * 手机号
     */
    @NotBlank(message = "手机号不能为空")
    @Pattern(regexp = "^[1][3-9][0-9]{9}$", message = "手机号格式有误")
    private String mobile;

    /**
     * 邮箱
     */
    @NotBlank(message = "联系邮箱不能为空")
    @Email(message = "邮箱格式不对")
    private String email;

    /*** 创建时间 */
    @Future(message = "时间必须是将来时间", groups = {Create.class})
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date createTime;

    @NotBlank(message = "身份证号不能为空")
    @IdentityCardNumber(message = "身份证信息有误,请核对后提交")
    private String clientCardNo;

    // 前端传入的flag值必须是1或2或3，否则校验失败
    @FlagValidator(values = "1,2,3")
    private String flag ;

    @NotEmpty(message = "订单集合不能为空")
    private List<@Valid @NotNull OrderDTO> orderDTOS;
}
