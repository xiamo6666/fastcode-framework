package com.ssos.formenginv2.entity;

import lombok.*;

/**
 * @ClassName: FieldValue
 * @Description: TODD
 * @Author: xwl
 * @Date: 2019-05-28 11:03
 * @Vsersion: 1.0
 */
@Setter
@Getter
@RequiredArgsConstructor(staticName = "of")
public class FieldValue {
    private Long id;
    @NonNull
    private Long tableId;
    @NonNull
    private String tableName;
    @NonNull
    private String content;
}
