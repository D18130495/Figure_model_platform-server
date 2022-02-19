package com.yushun.figure.user.config.api;

import com.yushun.figure.common.result.Result;
import com.yushun.figure.common.utils.AuthContextHolder;
import com.yushun.figure.user.service.PeopleService;
import com.yushun.figure.vo.user.People;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/user/people")
public class peopleController {

    @Autowired
    private PeopleService peopleService;

    // get order people list
    @GetMapping("auth/findAll")
    public Result findAllOrderPeople(HttpServletRequest request) {
        Long userId = AuthContextHolder.getUserId(request);
        List<People> list = peopleService.findAllUserId(userId);
        return Result.ok(list);
    }

    // add order people
    @PostMapping("/auth/save")
    public Result savePeople(@RequestBody People people,
                             HttpServletRequest request) {
        Long userId = AuthContextHolder.getUserId(request);
        people.setUserId(userId);
        peopleService.save(people);

        return Result.ok();
    }

    // get order people by id
    @GetMapping("/auth/get/{id}")
    public Result getPeople(@PathVariable Long id) {
        People people = peopleService.getPeopleById(id);
        return Result.ok(people);
    }

    // update order people
    @PutMapping("auth/update")
    public Result updatePeople(@RequestBody People people) {
        peopleService.updateById(people);
        return Result.ok();
    }

    // delete order people
    @DeleteMapping("auth/delete/{id}")
    public Result deletePeople(@PathVariable Long id) {
        peopleService.removeById(id);
        return Result.ok();
    }
}
