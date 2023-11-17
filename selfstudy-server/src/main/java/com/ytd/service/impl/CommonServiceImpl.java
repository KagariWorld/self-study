package com.ytd.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ytd.dto.SeatSelectByPageDTO;
import com.ytd.entity.Notice;
import com.ytd.entity.Seat;
import com.ytd.mapper.CommonMapper;
import com.ytd.result.PageResult;
import com.ytd.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CommonServiceImpl implements CommonService {
    @Autowired
    private CommonMapper commonMapper;
    @Override
    public PageResult selectSeatByArea(SeatSelectByPageDTO seatSelectByPageDTO) {
        PageHelper.startPage(seatSelectByPageDTO.getPage(),seatSelectByPageDTO.getPageSize()); //根据 当前页码，分页大小 通过分页查询 计算出该条件下的总分页数和每页的结果
        Page<Seat> page = commonMapper.selectSeatByPage(seatSelectByPageDTO);
        long total = page.getTotal(); //总条数
        List<Seat> records = page.getResult();
        return new PageResult(total,records);
    }

    @Override
    public List<Notice> selectNotices() {
        List<Notice> noticeList = commonMapper.selectAllNotices();
        return noticeList;
    }
}
