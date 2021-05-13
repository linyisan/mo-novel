package com.heng.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author LJohn
 * @since 2021-05-14
 */
@Data
public class UserUpdatePwdDTO {

    @NotNull(message = "用户ID不为空")
    private Long userId;

    @ApiModelProperty("新密码")
    @NotBlank(message = "新密码不能为空")
    private String newPassword;

    @ApiModelProperty("原密码")
    @NotBlank(message = "原密码不能为空")
    private String oldPassword;
}
