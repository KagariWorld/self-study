package com.ytd.service.impl;

import com.ytd.constant.MessageConstant;
import com.ytd.constant.StatusConstant;
import com.ytd.exception.AccountLockedException;
import com.ytd.exception.AccountNotFoundException;
import com.ytd.exception.PasswordErrorException;
import com.ytd.mapper.AdministerMapper;
import com.ytd.service.AdministerService;
import com.ytd.dto.AdministerLoginDTO;
import com.ytd.entity.Administer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

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
            return null;
            //账号不存在
//            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
        }

        //密码比对
        //进行md5加密，然后再进行比对
        //TODO
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        if (!password.equals(administer.getPassword())) {
            return null;
            //密码错误
//            throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);
        }

        if (administer.getStatus() == StatusConstant.DISABLE) {
            return null;
            //账号被锁定
//            throw new AccountLockedException(MessageConstant.ACCOUNT_LOCKED);
        }

        //3、返回实体对象
        return administer;
    }
}
