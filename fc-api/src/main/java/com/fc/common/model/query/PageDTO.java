package com.fc.common.model.query;


import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author xwl
 * @version 1.0
 * @since 2022/6/8 10:00
 */
@Setter
@Getter
@ToString
public class PageDTO {
    @ApiModelProperty("当前页")
    private Long current;

    @ApiModelProperty("每页大小")
    private Long size;

    @ApiModelProperty("正序")
    private Boolean asc;
}
