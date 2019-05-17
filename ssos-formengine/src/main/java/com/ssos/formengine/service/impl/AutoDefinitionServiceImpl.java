package com.ssos.formengine.service.impl;

import com.ssos.exception.BaseException;
import com.ssos.formengine.dto.AutoDefinitionDTO;
import com.ssos.formengine.entity.AutoDefinition;
import com.ssos.formengine.entity.FieldAssociate;
import com.ssos.formengine.mapper.AutoDefinitionMapper;
import com.ssos.formengine.mapper.FieldAssociateMapper;
import com.ssos.formengine.mapper.FieldMapper;
import com.ssos.formengine.service.AutoDefinitionService;
import com.ssos.formengine.utils.AsyncTransfer;
import com.ssos.formengine.utils.SqlUtils;
import com.ssos.formengine.vo.FieldShowVO;
import com.ssos.formengine.vo.FormAllShowVO;
import com.ssos.formengine.vo.FormOneShowVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        AutoDefinition autoDefinition = AutoDefinition.ofAutoDefinition(definitionDTO.getAutoTableName());
        // 插入一条定义
        autoDefinitionMapper.insert(autoDefinition);
        Set<Long> fieldIds = definitionDTO.getFieldIds();
        fieldIds.forEach((e) -> fieldAssociateMapper.insert(FieldAssociate.of(autoDefinition.getId(), e)));

        // 开始动态创建表
        if (!createTable(fieldIds, definitionDTO.getAutoTableName(), false)) {
            throw new BaseException("创建表的时候出错");
        }


        //开始解析子表
        List<AutoDefinitionDTO.SonDefinition> sonDefinitions = definitionDTO.getSonDefinitions();
        //遍历出每一个list中到的每一个子表字段
        sonDefinitions.forEach((p) -> {
            AutoDefinition sonDefinition = AutoDefinition.ofAutoDefinition(p.getAutoTableName())
                    .setParentId(autoDefinition.getId());
            //插入定义
            autoDefinitionMapper.insert(sonDefinition);
            Set<Long> sonFieldIds = p.getFieldIds();
            //插入定义字段关联
            sonFieldIds.forEach((ids) -> fieldAssociateMapper.insert(FieldAssociate.of(sonDefinition.getId(), ids)));
            //动态创建表，如果出错直接跑异常
            if (!createTable(sonFieldIds, p.getAutoTableName(), true)) {
                throw new BaseException("创建表的时候出错");
            }
        });
    }

    @Override
    public FormAllShowVO showtable(String name) {
        name = Objects.requireNonNull(name);
        List<FieldShowVO> fields = autoDefinitionMapper.showMarkField(name);
        if (fields == null || fields.size() == 0) {
            throw new BaseException("kong");
        }
        List<Map<String, Object>> values = autoDefinitionMapper.showValue(fields.get(0).getTableName());

        return FormAllShowVO.of(fields, values);
    }

    @Override
    public FormOneShowVO showOnetable(String tableName, Long id) {
        FormOneShowVO formOneShowVO = new FormOneShowVO();
        tableName = Objects.requireNonNull(tableName);
        //父表字段
        List<FieldShowVO> fields = autoDefinitionMapper.showNameField(tableName);
        //父表值
        List<Map<String, Object>> values = autoDefinitionMapper.showOneValue(tableName, id);

        formOneShowVO.setFormAllShowVO(FormAllShowVO.of(fields, values));

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
            //赋值子表值
            List<Map<String, Object>> sonValues = autoDefinitionMapper.showSonValue(x, id);
            FormAllShowVO.of(y, sonValues);
            sonFieldAndValule.add(FormAllShowVO.of(y, sonValues));
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


    /**
     * 动态创建表
     *
     * @param fieldIds
     * @param tableName
     * @return
     */
    private boolean createTable(Set<Long> fieldIds, String tableName, boolean isSon) {
        try {
            Boolean invoke = AsyncTransfer.invoke(() -> {
                String sql = SqlUtils.sqlHelper(() -> fieldMapper.findByIds(fieldIds), isSon);
                try {
                    autoDefinitionMapper.autoCreateTable(tableName, sql);
                } catch (Exception e) {
                    System.out.println(e.toString());
                    autoDefinitionMapper.dropTable(tableName);
                    return false;
                }
                return true;
            });
            if (!invoke.booleanValue()) {
                return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
