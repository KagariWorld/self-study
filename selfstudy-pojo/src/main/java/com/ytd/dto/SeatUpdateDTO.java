package com.ytd.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@ApiModel(description = "管理员修改座位信息传递的json")
public class SeatUpdateDTO implements Serializable {
    @ApiModelProperty("id")
    private Long id;
    @ApiModelProperty("座位区域")
    private String area;
    @ApiModelProperty("区域座位号")
    private String seatId;
    @ApiModelProperty("描述")
    private String description;
    @ApiModelProperty("状态")
    private Integer status;
    @ApiModelProperty("更新时间")
    private LocalDateTime updateTime;
}
