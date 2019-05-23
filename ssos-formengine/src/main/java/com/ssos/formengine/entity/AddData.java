package com.ssos.formengine.entity;

import lombok.*;

/**
 * @ClassName: AddData
 * @Description: TODD
 * @Author: xwl
 * @Date: 2019-05-23 15:05
 * @Vsersion: 1.0
 */
@Data
@RequiredArgsConstructor(staticName = "of")
@NoArgsConstructor
public class AddData {
    private Long id;
    @NonNull private String sql;
}
