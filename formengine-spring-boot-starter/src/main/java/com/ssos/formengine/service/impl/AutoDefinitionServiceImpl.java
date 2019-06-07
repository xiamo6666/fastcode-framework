package com.ssos.formengine.service.impl;

import com.ssos.exception.BaseException;
import com.ssos.formengine.dto.DataAddDTO;
import com.ssos.formengine.entity.AddData;
import com.ssos.formengine.mapper.AutoDefinitionMapper;
import com.ssos.formengine.mapper.FieldAssociateMapper;
import com.ssos.formengine.service.AutoDefinitionService;
import com.ssos.formengine.utils.SqlUtils;
import com.ssos.formengine.vo.*;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
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
    private AutoDefinitionMapper autoDefinitionMapper;
    @Autowired
    private FieldAssociateMapper fieldAssociateMapper;

    @Override
    public FormAllShowVO showtable(String mark) {
        mark = Objects.requireNonNull(mark);
        List<FieldShowVO> fields = autoDefinitionMapper.showMarkField(mark);
        if (fields == null || fields.size() == 0) {
            throw new BaseException("kong");
        }
        List<Map<String, Object>> values = autoDefinitionMapper.showValue(
                autoDefinitionMapper.queryTableName(fields.get(0).getTableName())
        );

        return FormAllShowVO.of(fields, values);
    }

    @Override
    public FormOneShowVO showOnetable(String name, Long id) {
        FormOneShowVO formOneShowVO = new FormOneShowVO();
        name = Objects.requireNonNull(name);
        //父表字段
        List<FieldShowVO> fields = autoDefinitionMapper.showNameField(name);
        //父表值
        String tableName = autoDefinitionMapper.queryTableName(name);
        if (tableName.isEmpty()) {
            throw new BaseException("数据在传输过程中被更改");
        }
        List<Map<String, Object>> values = autoDefinitionMapper.showOneValue(tableName, id);

        formOneShowVO.setFormAllShowVO(FormAllShowVO.of(fields, values));

        //子表查询
        //判断子表是否存在
        if (!(isExistSon(name))) {
            return formOneShowVO;
        }
        //创建子表字段和值集合
        List<FormAllShowVO> sonFieldAndValule = new ArrayList<>();
        List<FieldShowVO> sonField = autoDefinitionMapper.showSonField(tableName);
        //可能会查出多个子类，所以需要根据表名分组
        Map<String, List<FieldShowVO>> collect = sonField.stream()
                .collect(Collectors.groupingBy(FieldShowVO::getTableName));
        collect.forEach((x, y) -> {
            //赋值子表值
            List<Map<String, Object>> sonValues = autoDefinitionMapper.showSonValue(SqlUtils.caseTableName(x), id);
            FormAllShowVO.of(y, sonValues);
            sonFieldAndValule.add(FormAllShowVO.of(y, sonValues));
        });

        formOneShowVO.setAllShowVOS(sonFieldAndValule);
        return formOneShowVO;
    }

    @Override
    public FormAllFieldVO loadField(String mark) {
        //load 主表
        List<FieldLoadVo> fieldLoadVos = autoDefinitionMapper.loadMarkField(mark);
        FormAllFieldVO info = FormAllFieldVO.of(FormAllFieldVO.FormFileVO.of(fieldLoadVos));
        if (fieldLoadVos.size() == 0) {
            return info;
        }
        //判断当前表是否有子类
        String definitionName = fieldLoadVos.get(0).getName();
        if (!(isExistSon(definitionName))) {
            return info;
        }
        info.setSonFileVO(new ArrayList<>());
        //子表的字段
        List<FieldLoadVo> sonField = autoDefinitionMapper.loadSonField(definitionName);
        //根据表定义分组
        Map<String, List<FieldLoadVo>> collect = sonField.stream().collect(Collectors.groupingBy(FieldLoadVo::getName));
        //load 子表
        collect.forEach((x, y) -> {
            info.getSonFileVO().add(FormAllFieldVO.FormFileVO.of(y));
        });
        return info;
    }


    @Override
    public void addData(DataAddDTO dto) {
        //主表
        AddData insertSql = new AddData();
        DataAddDTO.DataInfos infos = dto.getInfos();
        infos.getValue().forEach(p -> {
            SQL sql = new SQL().INSERT_INTO(SqlUtils.caseTableName(infos.getName()));
            p.forEach((x, y) -> sql.VALUES(x, y));
            //执行添加操作
            insertSql.setSql(sql.toString());
            autoDefinitionMapper.insertData(insertSql);
        });
        // 子表
        dto.getSonInfos().forEach(p -> {
            String tableName = p.getName();
            p.getValue().forEach(e -> {
                SQL sql = new SQL().INSERT_INTO(SqlUtils.caseTableName(tableName));
                e.forEach((x, y) -> sql.VALUES(x, y));
                sql.VALUES("parent_id", insertSql.getId() + "");
                autoDefinitionMapper.insertData(AddData.of(sql.toString()));
            });
        });
    }


    /**
     * 根据表名去判断是否有子表存在
     *
     * @return
     */
    private boolean isExistSon(String definitionName) {
        String son = autoDefinitionMapper.isExistSon(definitionName);
        if (son != null && !(son.isEmpty())) {
            return true;
        }
        return false;
    }


}
