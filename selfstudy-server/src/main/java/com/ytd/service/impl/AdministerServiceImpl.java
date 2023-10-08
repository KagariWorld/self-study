package com.ytd.service.impl;

import com.ytd.constant.MessageConstant;
import com.ytd.constant.StatusConstant;
import com.ytd.context.BaseContext;
import com.ytd.dto.AdministerPasswordDTO;
import com.ytd.dto.AdministerRegisterDTO;
import com.ytd.dto.AdministerUpdateDTO;
import com.ytd.entity.User;
import com.ytd.exception.AccountLockedException;
import com.ytd.exception.AccountNotFoundException;
import com.ytd.exception.PasswordErrorException;
import com.ytd.mapper.AdministerMapper;
import com.ytd.service.AdministerService;
import com.ytd.dto.AdministerLoginDTO;
import com.ytd.entity.Administer;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AdministerServiceImpl implements AdministerService {
    @Autowired
    private AdministerMapper administerMapper;

    @Override
    public Administer login(AdministerLoginDTO administerLoginDTO) {
        String username = administerLoginDTO.getUsername();
        String password = administerLoginDTO.getPassword();

        //1、根据用户名查询数据库中的数据
        Administer administer = administerMapper.selectByUsername(username);

        //2、处理各种异常情况（用户名不存在、密码不对、账号被锁定）
        if (administer == null) {
            //账号不存在，抛出异常交给全局异常处理器
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
        }

        //密码比对
        //进行md5加密，然后再进行比对
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        if (!password.equals(administer.getPassword())) {
            //密码错误
            throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);
        }

        if (administer.getStatus() == StatusConstant.DISABLE) {
            //账号被锁定
            throw new AccountLockedException(MessageConstant.ACCOUNT_LOCKED);
        }

        //3、返回实体对象
        return administer;
    }

    @Override
    public void register(AdministerRegisterDTO administerRegisterDTO) {
        Administer administer = new Administer();
        //对象属性拷贝
        BeanUtils.copyProperties(administerRegisterDTO, administer);
        //默认状态启用
        administer.setStatus(StatusConstant.ENABLE);
        //md5加密
        administer.setPassword(DigestUtils.md5DigestAsHex(administer.getPassword().getBytes()));
        //设置当前记录的创建时间
        administer.setCreateTime(LocalDateTime.now());
        administerMapper.addAdminister(administer);
    }

    @Override
    public List<User> selectAll() {
        List<User> users = administerMapper.selectAll();
        return users;
    }

    @Override
    public Administer loadSelf() {
        Long currentId = BaseContext.getCurrentId();
        Administer administer = administerMapper.selectById(currentId);
        return administer;
    }

    @Override
    public void startOrStop(Integer status, Long id) {
        User user = User.builder()
                .status(status)
                .id(id)
                .build();
        administerMapper.updateUser(user);
    }

    @Override
    public void updateInformation(AdministerUpdateDTO administerUpdateDTO) {
        Administer administer = Administer.builder()
                .id(BaseContext.getCurrentId())
                .username(administerUpdateDTO.getUsername())
                .phone(administerUpdateDTO.getPhone())
                .sex(administerUpdateDTO.getSex())
                .build();
        administerMapper.updateAdminister(administer);
    }

    @Override
    public void updatePassword(AdministerPasswordDTO administerPasswordDTO) {
        String oldPassword = administerPasswordDTO.getOldPassword();
        //密码比对
        //进行md5加密，然后再进行比对
        oldPassword = DigestUtils.md5DigestAsHex(oldPassword.getBytes());
        if (!oldPassword.equals(administerMapper.selectById(BaseContext.getCurrentId()).getPassword())) {
            //密码错误
            throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);
        }
        //设置新密码
        String newPassword = administerPasswordDTO.getNewPassword();
        newPassword = DigestUtils.md5DigestAsHex(newPassword.getBytes());
        Administer administer = Administer.builder()
                .id(BaseContext.getCurrentId())
                .password(newPassword)
                .updateTime(LocalDateTime.now())
                .build();
        administerMapper.updateAdminister(administer);
    }
}
