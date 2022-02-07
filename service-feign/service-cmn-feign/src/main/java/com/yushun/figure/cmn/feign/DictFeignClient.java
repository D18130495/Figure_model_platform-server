package com.yushun.figure.cmn.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("service-cmn")
@Service
public interface DictFeignClient {
    @GetMapping("/admin/cmn/dict/getValue/{dictCode}/{value}")
    public String getDictValue(@PathVariable("dictCode") String dictCode, @PathVariable("value") String value);
}
