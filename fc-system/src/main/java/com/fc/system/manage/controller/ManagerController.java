package com.fc.system.manage.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

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

    @GetMapping("/test")
    public Map test() {
        return Map.of("data", "data");
    }
}
