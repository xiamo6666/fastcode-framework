package com.ssos.formengine.controller;

import com.ssos.formengine.dto.AutoDefinitionDTO;
import com.ssos.formengine.service.AutoDefinitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

@RestController
public class TestController {
    @Autowired
    AutoDefinitionService autoDefinitionService;
    @GetMapping("/test")
    public void Test(){
        AutoDefinitionDTO autoDefinitionDTO = new AutoDefinitionDTO();
        autoDefinitionDTO.setAutoTableName("test");
        Set<Long> set = new HashSet();
        set.add(1L);
        set.add(2L);
        autoDefinitionDTO.setFieldIds(set);
        autoDefinitionService.add(autoDefinitionDTO);
    }
}
