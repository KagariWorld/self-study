package com.ytd.controller;

import com.ytd.constant.JwtClaimsConstant;
import com.ytd.dto.*;
import com.ytd.entity.Administer;
import com.ytd.entity.Notice;
import com.ytd.entity.User;
import com.ytd.properties.JwtProperties;
import com.ytd.result.Result;
import com.ytd.service.AdministerService;
import com.ytd.utils.JwtUtil;
import com.ytd.vo.AdministerLoginVO;
import com.ytd.vo.UsersVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
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
        administerService.register(administerRegisterDTO);
        return Result.success();
    }
    @PostMapping("/login")
    @ApiOperation("管理员登录")
    public Result<AdministerLoginVO> login(@RequestBody AdministerLoginDTO administerLoginDTO) {
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
    @GetMapping("/selectAllUser")
    @ApiOperation("查询所有用户")
    public Result<UsersVO> selectAllUser(){
        List<User> users = administerService.selectAllUser();
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
    @ApiOperation("管理员修改密码")
    public Result updatePassword(@RequestBody AdministerPasswordDTO administerPasswordDTO) {
        administerService.updatePassword(administerPasswordDTO);
        return Result.success();
    }
    @PostMapping("/addSeat")
    @ApiOperation("管理员新增座位")
    public Result addSeat(@RequestBody SeatAddDTO seatAddDTO) {
        administerService.addSeat(seatAddDTO);
        return Result.success();
    }
    @PutMapping("/updateSeat")
    @ApiOperation("管理员修改座位")
    public Result updateSeat(@RequestBody SeatUpdateDTO seatUpdateDTO) {
        administerService.updateSeat(seatUpdateDTO);
        return Result.success();
    }
    @DeleteMapping("/deleteSeats")
    @ApiOperation("管理员删除座位")
    public Result deleteSeat(int[] ids) {
        administerService.deleteSeats(ids);
        return Result.success();
    }
    @PostMapping("/addNotice")
    @ApiOperation("管理员新增通知")
    public Result addNotice(@RequestBody Notice notice) {
        administerService.addNotice(notice);
        return Result.success();
    }
    @DeleteMapping("/deleteNotice/{id}")
    @ApiOperation("管理员删除通知")
    public Result deleteNotice(@PathVariable("id") Integer id) {
        administerService.deleteNotice(id);
        return Result.success();
    }
}
