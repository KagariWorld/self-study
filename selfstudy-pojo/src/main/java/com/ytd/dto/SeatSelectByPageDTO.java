package com.ytd.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
@Data
@ApiModel(description = "座位分页查询传递的json")
public class SeatSelectByPageDTO implements Serializable {
    @ApiModelProperty("座位区域")
    private String area;
    @ApiModelProperty("页号")
    private int page;
    @ApiModelProperty("每页大小")
    private int pageSize;

}
