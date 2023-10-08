package com.ytd.controller;

import com.ytd.constant.JwtClaimsConstant;
import com.ytd.dto.AdministerLoginDTO;
import com.ytd.dto.AdministerPasswordDTO;
import com.ytd.dto.AdministerRegisterDTO;
import com.ytd.dto.AdministerUpdateDTO;
import com.ytd.entity.Administer;
import com.ytd.entity.User;
import com.ytd.properties.JwtProperties;
import com.ytd.result.Result;
import com.ytd.service.AdministerService;
import com.ytd.utils.JwtUtil;
import com.ytd.vo.AdministerLoginVO;
import com.ytd.vo.UsersVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
@Slf4j
@Api(tags = "管理员相关接口")
// 只返回成功信息，失败信息交由全局异常处理器处理
public class AdministerController {
    @Autowired
    private AdministerService administerService;
    @Autowired
    private JwtProperties jwtProperties;

    @PostMapping("/register")
    @ApiOperation("管理员注册")
    public Result register(@RequestBody AdministerRegisterDTO administerRegisterDTO) {
        log.info("管理员注册：{}", administerRegisterDTO);
        administerService.register(administerRegisterDTO);
        return Result.success();
    }
    @PostMapping("/login")
    @ApiOperation("管理员登录")
    public Result<AdministerLoginVO> login(@RequestBody AdministerLoginDTO administerLoginDTO) {
        log.info("管理员登录：{}", administerLoginDTO);
        Administer administer = administerService.login(administerLoginDTO);
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
    @PostMapping("/logout")
    @ApiOperation("管理员退出")
    public Result logout() {
        return Result.success();
    }
    @GetMapping("/selectAll")
    @ApiOperation("查询所有用户")
    public Result<UsersVO> selectAll(){
        List<User> users = administerService.selectAll();
        UsersVO usersVO = UsersVO.builder()
                .users(users).build();
        return Result.success(usersVO);
    }

    @GetMapping("/loadSelf")
    @ApiOperation("加载自身的账户信息")
    public Result<Administer> loadSelf() {
        Administer administer = administerService.loadSelf();
        return Result.success(administer);
    }
    @PutMapping("/startOrStop/{status}")
    @ApiOperation("启用禁用员工账号")
    public Result startOrStop(@PathVariable("status") Integer status,@RequestParam("id") Long id) {
        administerService.startOrStop(status,id);
        return Result.success();
    }

    @PutMapping("/updateInformation")
    @ApiOperation("管理员修改信息")
    public Result updateInformation(@RequestBody AdministerUpdateDTO administerUpdateDTO) {
        administerService.updateInformation(administerUpdateDTO);
        return Result.success();
    }
    @PutMapping("/updatePassword")
    @ApiOperation("管理员修改信息")
    public Result updatePassword(@RequestBody AdministerPasswordDTO administerPasswordDTO) {
        log.info("{}",administerPasswordDTO);
        administerService.updatePassword(administerPasswordDTO);
        return Result.success();
    }
}
