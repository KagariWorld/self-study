package com.ytd.vo;

import com.ytd.entity.Notice;
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
@ApiModel(description = "返回的公告json数组")
public class NoticesVO {
    private List<Notice> noticeList;
}
