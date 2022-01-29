package com.yushun.figure.cmn.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yushun.figure.cmn.mapper.DictMapper;
import com.yushun.figure.cmn.service.DictService;
import com.yushun.figure.model.cmn.Dict;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements DictService {
    @Override
    public List<Dict> findChildData(Long id) {
        QueryWrapper<Dict> wrapper = new QueryWrapper<Dict>();
        wrapper.eq("parent_id", id);
        List<Dict> dictList = baseMapper.selectList(wrapper);

        dictList.stream().map(dict -> {
            dict.setHasChildren(hasChildren(dict.getId()));
            return dict;
        }).collect(Collectors.toList());

        return dictList;
    }

    private boolean hasChildren(Long id) {
        QueryWrapper<Dict> wrapper = new QueryWrapper<Dict>();
        wrapper.eq("parent_id", id);
        Integer count = baseMapper.selectCount(wrapper);

        if(count > 0) return true;
        else return false;
    }
}
