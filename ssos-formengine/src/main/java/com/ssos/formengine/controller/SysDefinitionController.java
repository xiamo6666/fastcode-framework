package com.ssos.formengine.controller;

import com.ssos.formengine.dto.AutoDefinitionDTO;
import com.ssos.formengine.service.SysDefinitionService;
import com.ssos.formengine.vo.FieldShowVO;
import com.ssos.formengine.vo.FieldVO;
import com.ssos.formengine.vo.SysDefinitionVO;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName: SysDefinitionAllController
 * @Description: 后台设置跟
 * @Author: xwl
 * @Date: 2019-05-21 15:08
 * @Vsersion: 1.0
 */
@RestController
@RequestMapping("/SysDefinition")
public class SysDefinitionController {

    @Autowired
    private SysDefinitionService sysDefinitionService;

    @ApiOperation("查询定义表列表（无子表）")
    @GetMapping("/findAll")
    public  List<SysDefinitionVO> findAll(){
       return  sysDefinitionService.SysDefinition();
    }
    @ApiOperation("根据id获取field字段列表")
    @GetMapping("/findFieldById")
   public List<FieldVO> findFieldById(@RequestParam Long id){
       return  sysDefinitionService.findFieldById(id);
    }
    @ApiOperation("定义添加已经动态创建表")
    @PostMapping("/addDefinition")
    public void add(@RequestBody  AutoDefinitionDTO autoDefinitionDTO){
        sysDefinitionService.add(autoDefinitionDTO);
    }
}
