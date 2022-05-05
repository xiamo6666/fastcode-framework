package com.fc.system.manage.controller;

import com.fc.common.model.LoginInfo;
import com.fc.common.model.Result;
import com.fc.core.filter.AuthFilter;
import com.fc.core.utils.UserUtils;
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
    public Result<String> test() {


        System.out.println(UserUtils.getUserId());
        return Result.success("test");
    }
}
