package com.ytd.service;

import com.ytd.dto.*;
import com.ytd.entity.User;

public interface UserService {
    /**
     * 用户注册
     * @param userRegisterDTO
     */
    void register(UserRegisterDTO userRegisterDTO);

    /**
     * 用户登录
     * @param userLoginDTO
     * @return User
     */
    User login(UserLoginDTO userLoginDTO);

    /**
     * 更新用户账户信息
     * @param userUpdateDTO
     * @return User
     */
    User updateInformation(UserUpdateDTO userUpdateDTO);
    /**
     * 更改密码
     * @param userPasswordDTO
     */
    void updatePassword(UserPasswordDTO userPasswordDTO);

    /**
     * 预约座位
     * @param id
     */
    void reserveSeat(Integer id);
}
