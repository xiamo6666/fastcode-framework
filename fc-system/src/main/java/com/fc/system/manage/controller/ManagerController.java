package com.fc.system.manage.controller;

import com.fc.common.model.LoginInfo;
import com.fc.common.model.Result;
import com.fc.core.filter.AuthFilter;
import com.fc.core.utils.UserUtils;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping("/test")
    public int test(@RequestBody Map<String, Object> params) {
        System.out.println(params);
        return 0;
    }
}
