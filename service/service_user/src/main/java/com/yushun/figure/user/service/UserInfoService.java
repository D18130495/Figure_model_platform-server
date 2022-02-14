package com.yushun.figure.user.service;

import com.yushun.figure.vo.user.LoginVo;

import java.util.Map;

public interface UserInfoService {
    Map<String, Object> loginUser(LoginVo loginVo);
}
