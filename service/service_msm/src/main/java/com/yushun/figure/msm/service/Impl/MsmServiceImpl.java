package com.yushun.figure.msm.service.Impl;

import com.yushun.figure.msm.service.MsmService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class MsmServiceImpl implements MsmService {
    @Override
    public boolean send(String phone, String code) {
        if(StringUtils.isEmpty(phone)) {
            return false;
        }
        // TODO
        return true;
    }
}
