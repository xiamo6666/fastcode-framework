package com.ssos.formengine.controller;

import com.ssos.formengine.dto.AutoDefinitionDTO;
import com.ssos.formengine.service.AutoDefinitionService;
import com.ssos.formengine.vo.FormAllShowVO;
import com.ssos.formengine.vo.FormOneShowVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
public class TestController {
    @Autowired
    private AutoDefinitionService autoDefinitionService;

    /**
     * 测试添加
     */
    @GetMapping("/add")
    public void Test() {
        AutoDefinitionDTO autoDefinitionDTO = new AutoDefinitionDTO();
        autoDefinitionDTO.setName("test");
        Set<Long> set = new HashSet();
        set.add(1L);
        set.add(2L);
        autoDefinitionDTO.setFieldIds(set);
        List<AutoDefinitionDTO.SonDefinition> sonDefinitions = new ArrayList<>();
        AutoDefinitionDTO.SonDefinition sonDefinition = new AutoDefinitionDTO.SonDefinition();
        sonDefinition.setName("testson");
        sonDefinition.setFieldIds(set);
        sonDefinitions.add(sonDefinition);
        autoDefinitionDTO.setSonDefinitions(sonDefinitions);
        autoDefinitionService.add(autoDefinitionDTO);
    }

    /**
     * 根据参数查询列表（目前参数是表名）
     *
     * @param name
     * @return
     */
    @GetMapping("/showtable")
    public FormAllShowVO showtable(String name) {
        return autoDefinitionService.showtable(name);
    }

    /**
     * 根据参数查询列表详情（参数是表名+详情id）
     */
    @GetMapping("/showOnetable")
    public FormOneShowVO showOneAll(String name, Long id) {
        return autoDefinitionService.showOnetable(name, id);
    }


}
