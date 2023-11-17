package com.ytd.service.impl;

import com.ytd.constant.MessageConstant;
import com.ytd.constant.StatusConstant;
import com.ytd.context.BaseContext;
import com.ytd.dto.*;
import com.ytd.entity.Administer;
import com.ytd.entity.Notice;
import com.ytd.entity.Seat;
import com.ytd.entity.User;
import com.ytd.exception.AccountLockedException;
import com.ytd.exception.AccountNotFoundException;
import com.ytd.exception.PasswordErrorException;
import com.ytd.mapper.AdministerMapper;
import com.ytd.service.AdministerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

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
        Administer administer = administerMapper.selectAdministerByUsername(username);

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

        administerMapper.addAdminister(administer);
    }

    @Override
    public List<User> selectAllUser() {
        List<User> users = administerMapper.selectAllUser();
        return users;
    }

    @Override
    public Administer loadSelf() {
        Long currentId = BaseContext.getCurrentId();
        Administer administer = administerMapper.selectAdministerById(currentId);
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
        if (!oldPassword.equals(administerMapper.selectAdministerById(BaseContext.getCurrentId()).getPassword())) {
            //密码错误
            throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);
        }
        //设置新密码
        String newPassword = administerPasswordDTO.getNewPassword();
        newPassword = DigestUtils.md5DigestAsHex(newPassword.getBytes());
        Administer administer = Administer.builder()
                .id(BaseContext.getCurrentId())
                .password(newPassword)
                .build();
        administerMapper.updateAdminister(administer);
    }

    @Override
    public void addSeat(SeatAddDTO seatAddDTO) {
        Seat seat = new Seat();
        BeanUtils.copyProperties(seatAddDTO, seat);
        seat.setStatus(StatusConstant.EMPTY);
        administerMapper.addSeat(seat);
    }

    @Override
    public void updateSeat(SeatUpdateDTO seatUpdateDTO) {
        Seat seat = new Seat();
        BeanUtils.copyProperties(seatUpdateDTO, seat);
        administerMapper.updateSeat(seat);
    }

    @Override
    public void deleteSeats(int[] ids) {
        administerMapper.deleteSeats(ids);
    }

    @Override
    public void addNotice(Notice notice) {
        administerMapper.addNotice(notice);
    }

    @Override
    public void deleteNotice(Integer id) {
        administerMapper.deleteNotice(id);
    }

}
