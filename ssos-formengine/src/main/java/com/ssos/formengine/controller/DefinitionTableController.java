package com.ssos.formengine.controller;

import com.ssos.base.model.DateResult;
import com.ssos.formengine.service.AutoDefinitionService;
import com.ssos.formengine.vo.FormAllFieldVO;
import com.ssos.formengine.vo.FormAllShowVO;
import com.ssos.formengine.vo.FormOneShowVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: DefinitionTableController
 * @Description: TODD
 * @Author: xwl
 * @Date: 2019-05-22 16:43
 * @Vsersion: 1.0
 */
@RestController
@RequestMapping("/definitionTable")
@Api(tags = "动态生成的表操作")
public class DefinitionTableController {
    @Autowired
    private AutoDefinitionService autoDefinitionService;

    @ApiOperation("根据标识查找数据列表")
    @GetMapping("/showTable")
    public DateResult<FormAllShowVO> showTable(@RequestParam String mark) {
        return DateResult.ok(autoDefinitionService.showtable(mark));
    }

    @ApiOperation("查看数据详情")
    @GetMapping("/showOneTable")
    public DateResult<FormOneShowVO> showOneTable(@RequestParam String tableName, @RequestParam Long id) {
        return DateResult.ok(autoDefinitionService.showOnetable(tableName, id));
    }

    @ApiOperation("通过标识加载动态表")
    @GetMapping("/load")
    public DateResult<FormAllFieldVO> load(@RequestParam  String mark){
        return null;
    }
}
