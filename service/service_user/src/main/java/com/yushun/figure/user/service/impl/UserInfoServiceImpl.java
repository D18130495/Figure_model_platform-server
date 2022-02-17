package com.yushun.figure.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yushun.figure.common.helper.JwtHelper;
import com.yushun.figure.common.result.AuthStatusEnum;
import com.yushun.figure.model.user.UserInfo;
import com.yushun.figure.user.mapper.UserInfoMapper;
import com.yushun.figure.user.service.UserInfoService;
import com.yushun.figure.vo.user.LoginVo;
import com.yushun.figure.vo.user.UserAuthVo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {
    @Override
    public Map<String, Object> loginUser(LoginVo loginVo) {
        // get loginVo phone number, and phone message
        String phone = loginVo.getPhone();
        String code = loginVo.getCode();

        // valuate if the phone number or phone message is empty
        if(StringUtils.isEmpty(phone) || StringUtils.isEmpty(code)) {
            System.out.println("Phone number or message is empty.");
        }

        // valuate if the phone message is equal to the input message
        // TODO
        if(!code.equals("777777")) {
            throw new NullPointerException();
        }

        // if first time use this phone number login, register
        QueryWrapper<UserInfo> wrapper = new QueryWrapper<UserInfo>();
        wrapper.eq("phone", phone);
        UserInfo userInfo = baseMapper.selectOne(wrapper);
        if(userInfo == null) {
            userInfo = new UserInfo();
            userInfo.setName("");
            userInfo.setPhone(phone);
            userInfo.setStatus(1);
            userInfo.setIsDeleted(0);
            userInfo.setCreateTime(new Date());
            userInfo.setUpdateTime(new Date());
            baseMapper.insert(userInfo);
        }


        // if not first time, login
        // return login user information
        // return login user name
        // return token
        Map<String, Object> result = new HashMap<String, Object>();
        String name = userInfo.getName();
        if(StringUtils.isEmpty(name)) {
            name = userInfo.getNickName();
        }
        if(StringUtils.isEmpty(name)) {
            name = userInfo.getPhone();
        }

        String token = JwtHelper.createToken(userInfo.getId(), name);
        result.put("name", name);
        result.put("token", token);

        return result;
    }

    @Override
    public void userAuth(Long userId, UserAuthVo userAuthVo) {
        // get user information
        UserInfo userInfo = baseMapper.selectById(userId);

        // set new information
        userInfo.setName(userAuthVo.getName());
        userInfo.setCertificatesType(userAuthVo.getCertificatesType());
        userInfo.setCertificatesNo(userAuthVo.getCertificatesNo());
        userInfo.setCertificatesUrl(userAuthVo.getCertificatesUrl());
        userInfo.setAuthStatus(AuthStatusEnum.AUTH_RUN.getStatus());

        baseMapper.updateById(userInfo);
    }

    @Override
    public UserInfo getUserInfoById(Long userId) {
        UserInfo userInfo = baseMapper.selectById(userId);
        return userInfo;
    }
}