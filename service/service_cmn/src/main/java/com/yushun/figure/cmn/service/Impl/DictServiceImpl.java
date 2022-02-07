package com.yushun.figure.cmn.service.Impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yushun.figure.cmn.mapper.DictMapper;
import com.yushun.figure.cmn.service.DictService;
import com.yushun.figure.model.cmn.Dict;
import com.yushun.figure.vo.cmn.DictExcelVo;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements DictService {
    @Override
//    @Cacheable(value = "dict", keyGenerator = "keyGenerator")
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

    @Override
    public void exportDictData(HttpServletResponse httpServletResponse) {
        httpServletResponse.setContentType("application/vnd.ms-excel");
        httpServletResponse.setCharacterEncoding("utf-8");
        String fileName = "Dict";
        httpServletResponse.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");

        List<Dict> dictList = baseMapper.selectList(null);
        List<DictExcelVo> dictExcelList = new ArrayList<DictExcelVo>();

        for(Dict dict : dictList) {
            DictExcelVo dictExcelVo = new DictExcelVo();
            BeanUtils.copyProperties(dict, dictExcelVo);
            dictExcelList.add(dictExcelVo);
        }

        try {
            EasyExcel.write(httpServletResponse.getOutputStream(), DictExcelVo.class).sheet("Dict").doWrite(dictExcelList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getDictValue(String dictCode, String value) {
        if(dictCode.equals("abc")) {
            QueryWrapper<Dict> wrapper = new QueryWrapper<Dict>();
            wrapper.eq("id", value);
            Dict dict = baseMapper.selectOne(wrapper);

            return dict.getName();
        }else {
            QueryWrapper<Dict> queryWrapper = new QueryWrapper<Dict>();
            queryWrapper.eq("dict_code", dictCode);
            Dict dict = baseMapper.selectOne(queryWrapper);
            Long dictId = dict.getId();

            QueryWrapper<Dict> wrapper = new QueryWrapper<Dict>();
            wrapper.eq("parent_id", dictId);
            wrapper.eq("value", value);
            Dict finalDict = baseMapper.selectOne(wrapper);

            return finalDict.getName();
        }
    }

    private boolean hasChildren(Long id) {
        QueryWrapper<Dict> wrapper = new QueryWrapper<Dict>();
        wrapper.eq("parent_id", id);
        Integer count = baseMapper.selectCount(wrapper);

        if(count > 0) return true;
        else return false;
    }
}
