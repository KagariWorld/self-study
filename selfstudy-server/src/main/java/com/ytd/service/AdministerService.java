package com.ytd.service;

import com.ytd.dto.AdministerLoginDTO;
import com.ytd.dto.AdministerPasswordDTO;
import com.ytd.dto.AdministerRegisterDTO;
import com.ytd.dto.AdministerUpdateDTO;
import com.ytd.entity.Administer;
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
    List<User> selectAll();

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
}
