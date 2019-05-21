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
        if (isExistSon(tableName)) {
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


}
