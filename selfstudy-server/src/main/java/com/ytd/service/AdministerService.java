package com.ytd.service;

import com.ytd.dto.AdministerLoginDTO;
import com.ytd.entity.Administer;


public interface AdministerService {
    Administer login(AdministerLoginDTO administerLoginDTO);
}
