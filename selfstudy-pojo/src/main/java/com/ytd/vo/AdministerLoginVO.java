package com.ytd.vo;

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
@ApiModel(description = "管理员登录返回的json")
public class AdministerLoginVO implements Serializable {
    @ApiModelProperty("主键值")
    private Long id;
    @ApiModelProperty("用户名")
    private String username;
    @ApiModelProperty("jwt令牌")
    private String token;
}
