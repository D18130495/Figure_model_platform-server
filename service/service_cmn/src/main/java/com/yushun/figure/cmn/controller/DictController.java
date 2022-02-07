package com.yushun.figure.cmn.controller;

import com.yushun.figure.cmn.service.DictService;
import com.yushun.figure.common.result.Result;
import com.yushun.figure.model.cmn.Dict;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/admin/cmn/dict")
@CrossOrigin
public class DictController {

    @Autowired
    private DictService dictService;

    @GetMapping("/findChildData/{id}")
    public Result findChildDataById(@PathVariable Long id) {
        List<Dict> list = dictService.findChildData(id);
        return Result.ok(list);
    }

    @GetMapping("/getValue/{dictCode}/{value}")
    public String getDictValue(@PathVariable String dictCode, @PathVariable String value) {
        String dictValue = dictService.getDictValue(dictCode, value);
        return dictValue;
    }

    @GetMapping("exportData")
    public void exportDict(HttpServletResponse httpServletResponse) {
        dictService.exportDictData(httpServletResponse);
    }
}
