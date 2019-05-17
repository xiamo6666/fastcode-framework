package com.ssos.formengine.vo;

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
    @NonNull private List<FieldShowVO> fields;
    @NonNull private List<Map<String,Object>> value;
}
