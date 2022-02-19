package com.yushun.figure.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yushun.figure.vo.user.People;

import java.util.List;

public interface PeopleService extends IService<People> {
    List<People> findAllUserId(Long userId);

    People getPeopleById(Long id);
}
