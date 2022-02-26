package com.yushun.figure.company.feign;

import com.yushun.figure.vo.company.ScheduleOrderVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(value = "service-company")
@Service
public interface CompanyFeignClient {
    @GetMapping("/admin/comp/schedule/inner/getScheduleOrderVo/{scheduleId}")
    public ScheduleOrderVo getScheduleOrderVo(@PathVariable("scheduleId") String scheduleId);
}
