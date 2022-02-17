package com.yushun.figure.user.service;

import com.yushun.figure.model.user.UserInfo;
import com.yushun.figure.vo.user.LoginVo;
import com.yushun.figure.vo.user.UserAuthVo;

import java.util.Map;

public interface UserInfoService {
    Map<String, Object> loginUser(LoginVo loginVo);

    void userAuth(Long userId, UserAuthVo userAuthVo);

    UserInfo getUserInfoById(Long userId);
}
