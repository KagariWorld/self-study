package com.ytd.controller;

import com.ytd.constant.JwtClaimsConstant;
import com.ytd.dto.*;
import com.ytd.entity.Administer;
import com.ytd.entity.User;
import com.ytd.properties.JwtProperties;
import com.ytd.result.Result;
import com.ytd.service.UserService;
import com.ytd.utils.JwtUtil;
import com.ytd.vo.AdministerLoginVO;
import com.ytd.vo.UserLoginVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Slf4j
@Api(tags = "用户相关接口")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtProperties jwtProperties;

    @PostMapping("/register")
    @ApiOperation("用户注册")
    public Result register(@RequestBody UserRegisterDTO userRegisterDTO) {
        userService.register(userRegisterDTO);
        return Result.success();
    }
    @PostMapping("/login")
    @ApiOperation("用户登录")
    public Result<UserLoginVO> login(@RequestBody UserLoginDTO userLoginDTO) {
        User user = userService.login(userLoginDTO);
        //登录成功后，生成jwt令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.ADMIN_ID, user.getId());
        String token = JwtUtil.createJWT(
                jwtProperties.getAdminSecretKey(),
                jwtProperties.getAdminTtl(),
                claims);
        UserLoginVO userLoginVO = UserLoginVO.builder()
                .user(user)
                .token(token)
                .build();
        return Result.success(userLoginVO);
    }

    @PutMapping("/updateInformation")
    @ApiOperation("用户修改信息")
    public Result updateInformation(@RequestBody UserUpdateDTO userUpdateDTO) {
        User user = userService.updateInformation(userUpdateDTO);
        return Result.success(user);
    }
    @PutMapping("/updatePassword")
    @ApiOperation("用户修改密码")
    public Result updatePassword(@RequestBody UserPasswordDTO userPasswordDTO) {
        userService.updatePassword(userPasswordDTO);
        return Result.success();
    }
    @PostMapping("/reserveSeat/{id}")
    @ApiOperation("用户预约座位")
    public Result reserveSeat(@PathVariable(value="id") Integer id) {
        userService.reserveSeat(id);
        return Result.success();
    }

}
