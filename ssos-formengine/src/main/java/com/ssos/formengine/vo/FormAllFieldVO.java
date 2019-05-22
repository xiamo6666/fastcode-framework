package com.ssos.formengine.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.List;

/**
 * @ClassName: FormAllFieldVO
 * @Description: TODD
 * @Author: xwl
 * @Date: 2019-05-22 17:50
 * @Vsersion: 1.0
 */
@Data
public class FormAllFieldVO {
    @ApiModelProperty("主表")
    private FormFileVO formFileVO;
    @ApiModelProperty("子表")
    private List<FormFileVO> sonFileVO;

    @Data
    public class FormFileVO {
        private List<FieldVO> fields;
    }
}
