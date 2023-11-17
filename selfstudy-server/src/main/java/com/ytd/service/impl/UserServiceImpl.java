package com.ytd.service.impl;

import com.ytd.constant.MessageConstant;
import com.ytd.constant.StatusConstant;
import com.ytd.context.BaseContext;
import com.ytd.dto.UserLoginDTO;
import com.ytd.dto.UserPasswordDTO;
import com.ytd.dto.UserRegisterDTO;
import com.ytd.dto.UserUpdateDTO;
import com.ytd.entity.Administer;
import com.ytd.entity.User;
import com.ytd.exception.AccountLockedException;
import com.ytd.exception.AccountNotFoundException;
import com.ytd.exception.PasswordErrorException;
import com.ytd.mapper.UserMapper;
import com.ytd.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public void register(UserRegisterDTO userRegisterDTO) {
        User user = new User();
        BeanUtils.copyProperties(userRegisterDTO, user);
        user.setStatus(StatusConstant.ENABLE);
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        userMapper.addUser(user);
    }

    @Override
    public User login(UserLoginDTO userLoginDTO) {
        String username = userLoginDTO.getUsername();
        String password = userLoginDTO.getPassword();
        //1、根据用户名查询数据库中的数据
        User user = userMapper.selectUserByUsername(username);

        //2、处理各种异常情况（用户名不存在、密码不对、账号被锁定）
        if (user == null) {
            //账号不存在，抛出异常交给全局异常处理器
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
        }

        //密码比对
        //进行md5加密，然后再进行比对
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        if (!password.equals(user.getPassword())) {
            //密码错误
            throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);
        }

        if (user.getStatus() == StatusConstant.DISABLE) {
            //账号被锁定
            throw new AccountLockedException(MessageConstant.ACCOUNT_LOCKED);
        }

        //3、返回实体对象
        return user;
    }

    @Override
    public User updateInformation(UserUpdateDTO userUpdateDTO) {
        User user = User.builder()
                .id(BaseContext.getCurrentId())
                .username(userUpdateDTO.getUsername())
                .phone(userUpdateDTO.getPhone())
                .sex(userUpdateDTO.getSex())
                .psnId(userUpdateDTO.getPsnId())
                .build();
        userMapper.updateUser(user);
        user = userMapper.selectUserById(BaseContext.getCurrentId());
        return user;
    }

    @Override
    public void updatePassword(UserPasswordDTO userPasswordDTO) {
        String oldPassword = userPasswordDTO.getOldPassword();
        //密码比对
        //进行md5加密，然后再进行比对
        oldPassword = DigestUtils.md5DigestAsHex(oldPassword.getBytes());
        if (!oldPassword.equals(userMapper.selectUserById(BaseContext.getCurrentId()).getPassword())) {
            //密码错误
            throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);
        }
        //设置新密码
        String newPassword = userPasswordDTO.getNewPassword();
        newPassword = DigestUtils.md5DigestAsHex(newPassword.getBytes());
        User user = User.builder()
                .id(BaseContext.getCurrentId())
                .password(newPassword)
                .build();
        userMapper.updateUser(user);
    }

    @Override
    public void reserveSeat(Integer id) {
        userMapper.reserveSeat(id);
    }
}
