package com.ssos.formengine.service.impl;

import com.ssos.exception.BaseException;
import com.ssos.formengine.dto.AutoDefinitionDTO;
import com.ssos.formengine.dto.SonAutoDefinitionDTO;
import com.ssos.formengine.dto.UpdateDefinitionDTO;
import com.ssos.formengine.entity.AutoDefinition;
import com.ssos.formengine.entity.FieldAssociate;
import com.ssos.formengine.mapper.AutoDefinitionMapper;
import com.ssos.formengine.mapper.FieldAssociateMapper;
import com.ssos.formengine.mapper.FieldMapper;
import com.ssos.formengine.service.SysDefinitionService;
import com.ssos.formengine.utils.AsyncTransfer;
import com.ssos.formengine.utils.SqlUtils;
import com.ssos.formengine.vo.FieldVO;
import com.ssos.formengine.vo.SysDefinitionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

/**
 * @ClassName: SysDefinitionServiceImpl
 * @Description: 配置自定义字段相关操作(设置中的操作)
 * @Author: xwl
 * @Date: 2019-05-21 15:01
 * @Vsersion: 1.0
 */
@Service
public class SysDefinitionServiceImpl implements SysDefinitionService {

    @Autowired
    private FieldAssociateMapper fieldAssociateMapper;

    @Autowired
    private AutoDefinitionMapper autoDefinitionMapper;

    @Autowired
    private FieldMapper fieldMapper;

    //定义列表的查看
    @Override
    public List<SysDefinitionVO> SysDefinition() {
        return fieldAssociateMapper.findAll();
    }

    @Override
    public List<FieldVO> findFieldById(Long id) {
        return fieldAssociateMapper.findFieldById(id);
    }

    //定义列表的修改
    @Override
    public void sysUpdateDefinition(UpdateDefinitionDTO updateDefinitionDTO) {

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(AutoDefinitionDTO definitionDTO) {
        //数据存入数据库
        String name = definitionDTO.getName();
        String tableName = SqlUtils.caseTableName(name);
        AutoDefinition autoDefinition = AutoDefinition.of(tableName, name);
        // 插入一条定义
        autoDefinitionMapper.insert(autoDefinition);
        Set<Long> fieldIds = definitionDTO.getFieldIds();
        fieldIds.forEach((e) -> fieldAssociateMapper.insert(FieldAssociate.of(autoDefinition.getId(), e)));

        // 开始动态创建表
        if (!createTable(fieldIds, tableName, false)) {
            throw new BaseException("创建表的时候出错");
        }


        //开始解析子表
        List<AutoDefinitionDTO.SonDefinition> sonDefinitions = definitionDTO.getSonDefinitions();
        //遍历出每一个list中到的每一个子表字段
        sonDefinitions.forEach((p) -> {
            String sonTableName = SqlUtils.caseTableName(p.getName());
            AutoDefinition sonDefinition = AutoDefinition.of(sonTableName, p.getName())
                    .setParentId(autoDefinition.getId());
            //插入定义
            autoDefinitionMapper.insert(sonDefinition);
            Set<Long> sonFieldIds = p.getFieldIds();
            //插入定义字段关联
            sonFieldIds.forEach((ids) -> fieldAssociateMapper.insert(FieldAssociate.of(sonDefinition.getId(), ids)));
            //动态创建表，如果出错直接跑异常
            if (!createTable(sonFieldIds, sonTableName, true)) {
                throw new BaseException("创建表的时候出错");
            }
        });
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void sonAdd(SonAutoDefinitionDTO sonAutoDefinition) {
        sonAutoDefinition.getSonDefinitions().forEach(p -> {
            String tableName = SqlUtils.caseTableName(p.getName());
            AutoDefinition autoDefinition = AutoDefinition.of(tableName, p.getName()).setParentId(sonAutoDefinition.getParentId());
            autoDefinitionMapper.insert(autoDefinition);
            Set<Long> fieldIds = p.getFieldIds();
            fieldIds.forEach(ids -> fieldAssociateMapper.insert(FieldAssociate.of(autoDefinition.getId(), ids)));
            //动态创建子表
            if (!createTable(fieldIds, tableName, true)) {
                throw new BaseException("创建表的时候出错");
            }
        });
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
                    autoDefinitionMapper.dropTable(SqlUtils.caseTableName(tableName));
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
