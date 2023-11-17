package com.ytd.service;

import com.ytd.dto.*;
import com.ytd.entity.Administer;
import com.ytd.entity.Notice;
import com.ytd.entity.Seat;
import com.ytd.entity.User;

import java.util.List;


public interface AdministerService {
    /**
     * 管理员登录
     * @param administerLoginDTO
     * @return
     */
    Administer login(AdministerLoginDTO administerLoginDTO);
    /**
     * 管理员注册
     * @param administerRegisterDTO
     */
    void register(AdministerRegisterDTO administerRegisterDTO);
    /**
     * 查询所有用户信息
     * @return
     */
    List<User> selectAllUser();
    /**
     * 加载自身账户信息
     * @return
     */
    Administer loadSelf();
    /**
     * 启用&禁用用户账号
     * @param status
     * @param id
     */
    void startOrStop(Integer status, Long id);
    /**
     * 更新管理员账户信息
     * @param administerUpdateDTO
     */
    void updateInformation(AdministerUpdateDTO administerUpdateDTO);
    /**
     * 更改密码
     * @param administerPasswordDTO
     */
    void updatePassword(AdministerPasswordDTO administerPasswordDTO);
    /**
     * 添加座位
     * @param seatAddDTO
     */
    void addSeat(SeatAddDTO seatAddDTO);
    /**
     * 修改座位
     * @param seatUpdateDTO
     */
    void updateSeat(SeatUpdateDTO seatUpdateDTO);
    /**
     * 删除座位
     * @param ids
     */
    void deleteSeats(int[] ids);

    /**
     * 增加通知
     * @param notice
     */
    void addNotice(Notice notice);

    /**
     * 删除通知
     * @param id
     */
    void deleteNotice(Integer id);
}
