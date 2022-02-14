package com.yushun.figure.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yushun.figure.model.user.UserInfo;
import com.yushun.figure.user.mapper.UserInfoMapper;
import com.yushun.figure.user.service.UserInfoService;
import org.springframework.stereotype.Service;

@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {
}
