package com.ytd.vo;

import com.ytd.entity.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "用户登录返回的json")
public class UserLoginVO implements Serializable {
    @ApiModelProperty("用户信息")
    private User user;
    @ApiModelProperty("jwt令牌")
    private String token;
}
