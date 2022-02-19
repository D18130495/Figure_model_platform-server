package com.yushun.figure.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yushun.figure.cmn.feign.DictFeignClient;
import com.yushun.figure.user.mapper.PeopleMapper;
import com.yushun.figure.user.service.PeopleService;
import com.yushun.figure.vo.user.People;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PeopleServiceImpl extends ServiceImpl<PeopleMapper, People> implements PeopleService {

    @Autowired
    private DictFeignClient dictFeignClient;

    @Override
    public List<People> findAllUserId(Long userId) {
        QueryWrapper<People> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId);
        List<People> peopleList = baseMapper.selectList(wrapper);

        peopleList.stream().forEach(item -> {
            this.packPeople(item);
        });

        return peopleList;
    }

    @Override
    public People getPeopleById(Long id) {
        return packPeople(baseMapper.selectById(id));
    }

    private People packPeople(People people) {
        String certificatesType = dictFeignClient.getDictValue("Certificates Type", people.getCertificatesType());

        people.getParam().put("certificatesType", certificatesType);
        return people;
    }
}
