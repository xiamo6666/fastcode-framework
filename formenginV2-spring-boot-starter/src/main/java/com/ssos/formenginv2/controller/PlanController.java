package com.ssos.formenginv2.controller;

import com.ssos.base.model.DataResult;
import com.ssos.formenginv2.dto.PlanDto;
import com.ssos.formenginv2.service.impl.PlanServiceImpl;
import com.ssos.formenginv2.vo.PlanVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName: testController
 * @Description: TODD
 * @Author: xwl
 * @Date: 2019-05-24 16:09
 * @Vsersion: 1.0
 */
@RestController
@RequestMapping("/test")
@Api(tags = "test")
public class PlanController {
    @Autowired
    PlanServiceImpl planService;

    @Operation("计划查询")
    @GetMapping("/find")
    public DataResult<List<PlanVo>> test() {
        return DataResult.ok(planService.select());
    }

    @Operation("tests" )
    @PostMapping("/add")
    public DataResult add(@RequestBody PlanDto dto) {
        planService.add(dto);
        return DataResult.ok();

    }
}
