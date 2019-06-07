package com.ssos.formengine.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: FormAllShowVO
 * @Description: TODD
 * @Author: xwl
 * @Date: 2019-05-15 16:41
 * @Vsersion: 1.0
 */
@Setter
@Getter
@RequiredArgsConstructor(staticName = "of")
public class FormAllShowVO {
    @ApiModelProperty("字段")
    @NonNull private List<FieldShowVO> fields;
    @ApiModelProperty("字段值")
    @NonNull private List<Map<String,Object>> value;
}
