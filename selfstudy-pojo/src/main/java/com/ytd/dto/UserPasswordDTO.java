package com.ytd.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
@Data
@ApiModel(description = "用户修改密码传递的json")
public class UserPasswordDTO implements Serializable {
    @ApiModelProperty("原密码")
    private String oldPassword;
    @ApiModelProperty("新密码")
    private String newPassword;
}
