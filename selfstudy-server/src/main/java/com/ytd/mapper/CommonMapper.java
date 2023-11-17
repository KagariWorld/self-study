package com.ytd.mapper;

import com.github.pagehelper.Page;
import com.ytd.dto.SeatSelectByPageDTO;
import com.ytd.entity.Notice;
import com.ytd.entity.Seat;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CommonMapper {
    /**
     * 查询所有座位信息（分页查询）
     * @return 每页座位信息
     */
    Page<Seat> selectSeatByPage(SeatSelectByPageDTO seatSelectByPageDTO);

    /**
     * 查询所有通知
     * @return List<Notice>
     */
    @Select("select * from notice")
    List<Notice> selectAllNotices();
}
