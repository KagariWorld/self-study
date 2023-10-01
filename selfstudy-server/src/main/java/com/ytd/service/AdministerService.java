package com.ytd.service;

import com.ytd.dto.AdministerLoginDTO;
import com.ytd.dto.AdministerRegisterDTO;
import com.ytd.entity.Administer;
import com.ytd.entity.User;

import java.util.List;


public interface AdministerService {
    Administer login(AdministerLoginDTO administerLoginDTO);
    void register(AdministerRegisterDTO administerRegisterDTO);
    List<User> selectAll();
    Administer selectById();
}
