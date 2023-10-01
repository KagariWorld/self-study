package com.ytd.vo;

import com.ytd.entity.Administer;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "返回的管理员json数组")
public class AdministersVO {
    private List<Administer> administers;
}
