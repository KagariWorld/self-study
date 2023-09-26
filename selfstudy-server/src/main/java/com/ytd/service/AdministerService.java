package com.ytd.service;

import com.ytd.dto.AdministerLoginDTO;
import com.ytd.dto.AdministerRegisterDTO;
import com.ytd.entity.Administer;


public interface AdministerService {
    Administer login(AdministerLoginDTO administerLoginDTO);
    boolean register(AdministerRegisterDTO administerRegisterDTO);
}
