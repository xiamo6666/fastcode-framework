package com.fc.system.manage.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: ManagerController
 * @Description: dto
 * @Author: xwl
 * @Date: 2022/4/25 14:01
 * @Vsersion: 1.0
 */
@RestController
@RequestMapping("/manager")
public class ManagerController {
    @PostMapping("/test")
    public int test(@RequestBody LiveParamDto params) {
        System.out.println(params);
        return 0;
    }
}
