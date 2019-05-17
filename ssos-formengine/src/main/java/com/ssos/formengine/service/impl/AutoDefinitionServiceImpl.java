package com.ssos.formengine.service.impl;

import com.ssos.exception.BaseException;
import com.ssos.formengine.dto.AutoDefinitionDTO;
import com.ssos.formengine.entity.AutoDefinition;
import com.ssos.formengine.entity.FieldAssociate;
import com.ssos.formengine.mapper.AutoDefinitionMapper;
import com.ssos.formengine.mapper.FieldAssociateMapper;
import com.ssos.formengine.mapper.FieldMapper;
import com.ssos.formengine.service.AutoDefinitionService;
import com.ssos.formengine.utils.SqlUtils;
import com.ssos.formengine.vo.FieldShowVO;
import com.ssos.formengine.vo.FormAllShowVO;
import com.ssos.formengine.vo.FormOneShowVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @ClassName: AutoDefinitionServiceImpl
 * @Description: TODD
 * @Author: xwl
 * @Date: 2019-05-13 15:29
 * @Vsersion: 1.0
 */
@Service
public class AutoDefinitionServiceImpl implements AutoDefinitionService {

    @Autowired
    AutoDefinitionMapper autoDefinitionMapper;
    @Autowired
    FieldAssociateMapper fieldAssociateMapper;
    @Autowired
    FieldMapper fieldMapper;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(AutoDefinitionDTO definitionDTO) {
        //数据存入数据库
        AutoDefinition autoDefinition = new AutoDefinition();
        autoDefinition.setAutoTableName(definitionDTO.getAutoTableName());
        // 插入一条定义
        autoDefinitionMapper.insert(autoDefinition);
        Set<Long> fieldIds = definitionDTO.getFieldIds();
        if (fieldIds == null || fieldIds.size() == 0) {
            System.out.println("参数为空");
            return;
        }
        FieldAssociate fieldAssociate = new FieldAssociate();
        fieldAssociate.setDefinitionTableId(autoDefinition.getId());
        fieldIds.forEach(p -> {
            fieldAssociate.setId(null);
            fieldAssociate.setFieldId(p);
            fieldAssociateMapper.insert(fieldAssociate);
        });
        // 开始动态创建表
        String sql = SqlUtils.sqlHelper(() -> fieldMapper.findByIds(fieldIds), false);
        autoDefinitionMapper.autoCreateTable(definitionDTO.getAutoTableName(), sql);

        //开始解析子表
        List<AutoDefinitionDTO.SonDefinition> sonDefinitions = definitionDTO.getSonDefinitions();
        sonDefinitions.forEach((p) -> {
            AutoDefinition sonDefinition = new AutoDefinition();
            sonDefinition.setAutoTableName(p.getAutoTableName());
            sonDefinition.setParentId(autoDefinition.getId());
            autoDefinitionMapper.insert(sonDefinition);
            FieldAssociate sonFieldAssociate = new FieldAssociate();
            sonFieldAssociate.setDefinitionTableId(sonDefinition.getId());
            Set<Long> sonFieldIds = p.getFieldIds();
            sonFieldIds.forEach((ids -> {
                sonFieldAssociate.setId(null);
                sonFieldAssociate.setFieldId(ids);
                fieldAssociateMapper.insert(sonFieldAssociate);
            }));
            String sonSql = SqlUtils.sqlHelper(() -> fieldMapper.findByIds(sonFieldIds), true);
            autoDefinitionMapper.autoCreateTable(p.getAutoTableName(), sonSql);
        });
    }

    @Override
    public FormAllShowVO showtable(String name) {
        name = Objects.requireNonNull(name);
        FormAllShowVO formAllShowVO = new FormAllShowVO();
        List<FieldShowVO> fieldShowVOS = autoDefinitionMapper.showMarkField(name);
        if (fieldShowVOS == null || fieldShowVOS.size() == 0) {
            throw new BaseException("kong");
        }
        formAllShowVO.setFields(fieldShowVOS);
        formAllShowVO.setValue(autoDefinitionMapper.showValue(fieldShowVOS.get(0).getTableName()));
        return formAllShowVO;
    }

    @Override
    public FormOneShowVO showOnetable(String tableName, Long id) {
        FormOneShowVO formOneShowVO = new FormOneShowVO();
        tableName = Objects.requireNonNull(tableName);
        //父表赋值
        FormAllShowVO formAllShowVO = new FormAllShowVO();
        //父表字段
        formAllShowVO.setFields(autoDefinitionMapper.showNameField(tableName));
        //父表值
        formAllShowVO.setValue(autoDefinitionMapper.showOneValue(tableName, id));
        formOneShowVO.setFormAllShowVO(formAllShowVO);

        //子表查询
        //判断子表是否存在
        if (isExistSon(tableName)) {
            return formOneShowVO;
        }
        //创建子表字段和值集合
        List<FormAllShowVO> sonFieldAndValule = new ArrayList<>();
        List<FieldShowVO> sonField = autoDefinitionMapper.showSonField(tableName);
        //可能会查出多个子类，所以需要根据表名分组
        Map<String, List<FieldShowVO>> collect = sonField.stream().collect(Collectors.groupingBy(FieldShowVO::getTableName));
        collect.forEach((x, y) -> {
            FormAllShowVO sonAll = new FormAllShowVO();
            //赋值子表字段
            sonAll.setFields(y);
            //赋值子表值
            sonAll.setValue(autoDefinitionMapper.showSonValue(x, id));
            sonFieldAndValule.add(sonAll);
        });

        formOneShowVO.setAllShowVOS(sonFieldAndValule);
        return formOneShowVO;
    }

    /**
     * 根据表名去判断是否有子表存在
     *
     * @return
     */
    private boolean isExistSon(String tableName) {
        String son = autoDefinitionMapper.isExistSon(tableName);
        if (son != null && son.isEmpty()) {
            return true;
        }
        return false;
    }
}
