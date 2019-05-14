package com.ssos.formengine.service.impl;

import com.ssos.formengine.dto.AutoDefinitionDTO;
import com.ssos.formengine.entity.AutoDefinition;
import com.ssos.formengine.entity.FieldAssociate;
import com.ssos.formengine.mapper.AutoDefinitionMapper;
import com.ssos.formengine.mapper.FieldAssociateMapper;
import com.ssos.formengine.mapper.FieldMapper;
import com.ssos.formengine.service.AutoDefinitionService;
import com.ssos.formengine.utils.SqlUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

/**
 * @ClassName: AutoDefinitionServiceImpl
 * @Description: TODD
 * @Author: xwl
 * @Date: 2019-05-13 15:29
 * @Vsersion: 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AutoDefinitionServiceImpl implements AutoDefinitionService {

    @Autowired
    AutoDefinitionMapper autoDefinitionMapper;
    @Autowired
    FieldAssociateMapper fieldAssociateMapper;
    @Autowired
    FieldMapper fieldMapper;


    @Override
    public void add(AutoDefinitionDTO definitionDTO) {
        //数据存入数据库
        AutoDefinition autoDefinition = new AutoDefinition();
        autoDefinition.setAutoTableName(definitionDTO.getAutoTableName());
        autoDefinitionMapper.insert(autoDefinition);
        Set<Long> fieldIds = definitionDTO.getFieldIds();
        if (fieldIds == null || fieldIds.size() == 0) {
            System.out.println("参数为空");
            return;
        }
        FieldAssociate fieldAssociate = new FieldAssociate();
        fieldAssociate.setDefinitionTableId(autoDefinition.getId());
        fieldIds.forEach(p -> {
            fieldAssociate.setFieldId(p);
            fieldAssociateMapper.insert(fieldAssociate);
        });
        // 开始动态创建表
        String sql = SqlUtils.sqlHelper(() -> fieldMapper.findByIds(fieldIds));
        autoDefinitionMapper.autoCreateTable(definitionDTO.getAutoTableName(),sql);
    }
}
