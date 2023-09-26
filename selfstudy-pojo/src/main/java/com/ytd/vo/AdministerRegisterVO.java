package com.ytd.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@ApiModel
public class AdministerRegisterVO {
    @ApiModelProperty("主键值")
    private Long id;
    @ApiModelProperty("用户名")
    private String username;
    @ApiModelProperty("jwt令牌")
    private String token;
}
