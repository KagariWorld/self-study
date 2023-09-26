package com.ytd.controller;

import com.ytd.constant.JwtClaimsConstant;
import com.ytd.dto.AdministerLoginDTO;
import com.ytd.dto.AdministerRegisterDTO;
import com.ytd.entity.Administer;
import com.ytd.properties.JwtProperties;
import com.ytd.result.Result;
import com.ytd.service.AdministerService;
import com.ytd.utils.JwtUtil;
import com.ytd.vo.AdministerLoginVO;
import com.ytd.vo.AdministerRegisterVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin")
@Slf4j
@Api(tags = "管理员相关接口")
public class AdministerController {
    @Autowired
    private AdministerService administerService;
    @Autowired
    private JwtProperties jwtProperties;
    @PostMapping("/register")
    @ApiOperation("管理员注册")
    public Result<AdministerRegisterVO> register(@RequestBody AdministerRegisterDTO administerRegisterDTO){
        log.info("管理员登录：{}",administerRegisterDTO);
        boolean flag = administerService.register(administerRegisterDTO);
        if(flag){
            return Result.success();
        }
        return null;
    }
    @PostMapping("/login")
    @ApiOperation("管理员登录")
    public Result<AdministerLoginVO> login(@RequestBody AdministerLoginDTO administerLoginDTO){
        log.info("管理员登录：{}", administerLoginDTO);
        Administer administer = administerService.login(administerLoginDTO);
        if(null == administer) {
            return Result.error("密码错误或账户不存在！");
        }
        //登录成功后，生成jwt令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.ADMIN_ID, administer.getId());
        String token = JwtUtil.createJWT(
                jwtProperties.getAdminSecretKey(),
                jwtProperties.getAdminTtl(),
                claims);
        AdministerLoginVO administerLoginVO = AdministerLoginVO.builder()
                .id(administer.getId())
                .username(administer.getUsername())
                .token(token)
                .build();

        return Result.success(administerLoginVO);
    }

    /**
     * 退出
     *
     * @return
     */
    @PostMapping("/logout")
    @ApiOperation("管理员退出")
    public Result<String> logout() {
        return Result.success();
    }
}
