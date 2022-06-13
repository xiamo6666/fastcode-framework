package com.fc.common.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author xwl
 * @version 1.0
 * @since 2022/6/8 10:08
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PageResult<T> {
    /**
     * 当前页数
     */
    @Schema(description = "当前页")
    private Long current;

    @Schema(description = "每页大小")
    private Long size;
    /**
     * 总记录数
     */

    @Schema(description = "总数")
    private Long total;
    /**
     * 每行显示内容
     */
    @Schema(description = "分页数据")
    private List<T> records;


}
