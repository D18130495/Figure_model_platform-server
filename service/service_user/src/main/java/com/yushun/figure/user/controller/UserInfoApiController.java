package com.yushun.figure.user.controller;

import com.yushun.figure.common.result.Result;
import com.yushun.figure.user.service.UserInfoService;
import com.yushun.figure.vo.user.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserInfoApiController {

    @Autowired
    private UserInfoService userInfoService;

    @PostMapping("login")
    public Result login(@RequestBody LoginVo loginVo) {
        Map<String, Object> result = userInfoService.loginUser(loginVo);
        return Result.ok(result);
    }
}
