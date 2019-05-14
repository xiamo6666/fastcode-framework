package com.ssos.formengine.entity;


import lombok.Data;

import java.time.LocalDateTime;

/**
 * @ClassName: Field
 * @Description: TODD
 * @Author: xwl
 * @Date: 2019-05-10 17:24
 * @Vsersion: 1.0
 */
@Data
public class Field {
    private Long id;
    private String fieldName;
    private String fieldMark;
    private Integer fieldTypeId;
    private String fieldDefaultValue;
    private Integer fieldMin;
    private Integer fieldMax;
    private Integer isQuery;
    private LocalDateTime createTime;

}
