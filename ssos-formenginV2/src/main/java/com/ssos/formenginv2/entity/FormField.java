package com.ssos.formenginv2.entity;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * @ClassName: FormField
 * @Description: TODD
 * @Author: xwl
 * @Date: 2019-06-03 13:45
 * @Vsersion: 1.0
 */
@Getter
@Setter
@RequiredArgsConstructor(staticName = "of")
public class FormField {
    private Long id;
    @NonNull private Long formId;
    @NonNull private Long fieldId;
}
