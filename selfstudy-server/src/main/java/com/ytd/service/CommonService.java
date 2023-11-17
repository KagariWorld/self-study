package com.ytd.service;

import com.ytd.dto.SeatSelectByPageDTO;
import com.ytd.entity.Notice;
import com.ytd.result.PageResult;

import java.util.List;

public interface CommonService {
    /**
     * 分页查询座位信息
     * @param seatSelectByPageDTO
     * @return
     */
    PageResult selectSeatByArea(SeatSelectByPageDTO seatSelectByPageDTO);

    List<Notice> selectNotices();
}
