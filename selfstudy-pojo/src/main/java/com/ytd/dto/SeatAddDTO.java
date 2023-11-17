package com.ytd.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
@Data
@ApiModel(description = "管理员添加座位传递的json")
public class SeatAddDTO implements Serializable {
    @ApiModelProperty("区域")
    private String area;
    @ApiModelProperty("座位号")
    private String seatId;
    @ApiModelProperty("图片路径")
    private String img;
    @ApiModelProperty("描述")
    private String description;
}
