package com.ssos.formengine.vo;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: FormAllShowVO
 * @Description: TODD
 * @Author: xwl
 * @Date: 2019-05-15 16:41
 * @Vsersion: 1.0
 */
@Data
public class FormAllShowVO {
    private List<FieldShowVO> fields;
    private List<Map<String,Object>> value;
}
