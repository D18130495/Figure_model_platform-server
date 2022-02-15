package com.yushun.figure.msm.controller;

import com.yushun.figure.common.result.Result;
import com.yushun.figure.msm.service.MsmService;
import com.yushun.figure.msm.utils.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api/msm")
public class MsmController {

    @Autowired
    private MsmService msmService;

//    @Autowired
//    private RedisTemplate<String, String> redisTemplate;

    @GetMapping("send/{phone}")
    public Result sendCode(@PathVariable String phone) {
//        String code = redisTemplate.opsForValue().get(phone);
//        if(!StringUtils.isEmpty(code)) {
//            return Result.ok();
//        }

//        code = RandomUtil.getSixBitRandom();
        String code = "777777";
//        boolean isSend = msmService.send(phone, code);
//
//        if(isSend) {
//            redisTemplate.opsForValue().set(phone, code, 2, TimeUnit.MINUTES);
//            return Result.ok(code);
//        }else {
//            return Result.fail().message("Fail to send message");
//        }
        return Result.ok(code);
    }
}
