package com.ssos.formenginv2.entity;

import lombok.*;
import lombok.experimental.Accessors;

/**
 * @ClassName: FieldRelationMapper
 * @Description: TODD
 * @Author: xwl
 * @Date: 2019-05-31 16:29
 * @Vsersion: 1.0
 */
@Getter
@Setter
@RequiredArgsConstructor(staticName = "ofTableName")
@Accessors(chain = true)
public class FieldRelation {
    private Long id;
    @NonNull
    private String tableName;
    private String tableMark;
}
