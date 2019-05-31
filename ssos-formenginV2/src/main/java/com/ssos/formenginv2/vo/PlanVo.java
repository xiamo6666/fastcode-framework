package com.ssos.formenginv2.vo;

import com.ssos.formenginv2.entity.BaseField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @ClassName: PlanVo
 * @Description: TODD
 * @Author: xwl
 * @Date: 2019-05-24 16:07
 * @Vsersion: 1.0
 */
@Setter
@Getter
public class PlanVo  extends BaseField {
    @ApiModelProperty("这是名称")
    private  String name;
}
