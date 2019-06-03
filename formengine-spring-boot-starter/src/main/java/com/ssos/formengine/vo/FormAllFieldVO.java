package com.ssos.formengine.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @ClassName: FormAllFieldVO
 * @Description: TODD
 * @Author: xwl
 * @Date: 2019-05-22 17:50
 * @Vsersion: 1.0
 */
@Setter
@Getter
@RequiredArgsConstructor(staticName = "of")
public class FormAllFieldVO {
    @ApiModelProperty("主表")
    @NonNull
    private FormFileVO formFileVO;
    @ApiModelProperty("子表")
    private List<FormFileVO> sonFileVO;

    @Setter
    @Getter
    @RequiredArgsConstructor(staticName = "of")
    public static class FormFileVO {
        @ApiModelProperty("字段类容")
        @NonNull
        private List<FieldLoadVo> fields;
    }
}
