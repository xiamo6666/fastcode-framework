package com.ssos.formenginv2.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @ClassName: BaseField
 * @Description: TODD
 * @Author: xwl
 * @Date: 2019-05-24 15:59
 * @Vsersion: 1.0
 */
@Getter
@Setter
public abstract class BaseField {

    private Long id;
    @ApiModelProperty("这是json字段")
    protected String json;
}
