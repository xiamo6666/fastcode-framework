package com.ssos.formenginv2.controller;

import com.ssos.base.model.DataResult;
import com.ssos.formenginv2.dto.FormFieldAddDto;
import com.ssos.formenginv2.service.FormenginConfigService;
import com.ssos.formenginv2.vo.FieldInfoVo;
import com.ssos.formenginv2.vo.FieldRelationVo;
import com.ssos.formenginv2.vo.FieldVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName: FormenginConfigController
 * @Description: TODD
 * @Author: xwl
 * @Date: 2019-05-31 18:27
 * @Vsersion: 1.0
 */
@Api(tags = "动态表单配置")
@RequestMapping("/config")
@RestController
public class FormenginConfigController {
    @Autowired
    private FormenginConfigService configService;

    @GetMapping("/all")
    @ApiOperation("获取所有的动态表单")
    public DataResult<List<FieldRelationVo>> findAll() {
        return DataResult.ok(configService.findAll());
    }

    @GetMapping("/findField")
    @ApiOperation("根据id找字段")
    public DataResult<List<FieldVo>> findFieleById(@RequestParam @ApiParam("id") Long id) {
        return DataResult.ok(configService.findFieleById(id));
    }

    @ApiOperation("为表单添加动态字段")
    @PostMapping("/addField")
    public DataResult addField(@RequestBody FormFieldAddDto dto) {
        configService.addField(dto);
        return DataResult.ok();
    }

    @ApiOperation("load表单字段")
    @GetMapping("/loadField")
    public DataResult<List<FieldInfoVo>> loadField(@RequestParam String mark) {
        return DataResult.ok(configService.loadField(mark));
    }

}
