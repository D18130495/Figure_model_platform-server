package com.yushun.figure.user.feign;

import com.yushun.figure.vo.user.People;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "service-user")
@Service
public interface PeopleFeignClient {
    @GetMapping("/api/user/people/inner/get/{peopleId}")
    public People getPeopleOrder(@PathVariable("peopleId") Long peopleId);
}
