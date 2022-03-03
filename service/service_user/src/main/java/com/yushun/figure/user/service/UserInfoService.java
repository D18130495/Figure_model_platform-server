package com.yushun.figure.user.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yushun.figure.model.user.UserInfo;
import com.yushun.figure.vo.user.LoginVo;
import com.yushun.figure.vo.user.UserAuthVo;
import com.yushun.figure.vo.user.UserQueryVo;

import java.util.Map;

public interface UserInfoService extends IService<UserInfo> {
    Map<String, Object> loginUser(LoginVo loginVo);

    void userAuth(Long userId, UserAuthVo userAuthVo);

    UserInfo getUserInfoById(Long userId);

    IPage<UserInfo> selectPage(Page<UserInfo> page, UserQueryVo userQueryVo);

    void lockOrUnlockUser(long userId, Integer status);

    void authUser(long userId, Integer authStatus);
}
