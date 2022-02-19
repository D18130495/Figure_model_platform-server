package com.yushun.figure.user.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yushun.figure.common.result.Result;
import com.yushun.figure.model.user.UserInfo;
import com.yushun.figure.user.service.UserInfoService;
import com.yushun.figure.vo.user.UserQueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/user")
public class UserController {

    @Autowired
    private UserInfoService userInfoService;

    // user list
    @GetMapping("{current}/{limit}")
    public Result getUserList(@PathVariable long current,
                              @PathVariable long limit,
                              UserQueryVo userQueryVo) {
        Page<UserInfo> page = new Page<>(current,limit);
        IPage<UserInfo> resultPage = userInfoService.selectPage(page, userQueryVo);

        return Result.ok(resultPage);
    }

    @PutMapping("lockOrUnlock/{userId}/{status}")
    public Result lockOrUnlockUser(@PathVariable long userId,
                           @PathVariable Integer status) {
        userInfoService.lockOrUnlockUser(userId, status);
        return Result.ok();
    }
}
