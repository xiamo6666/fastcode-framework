package com.fc.system.dict.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author xwl
 * @version 1.0
 * @since 2022/6/8 13:51
 */
@Data
public class DictInfoDTO {
    @ApiModelProperty("字典索引名称")
    private String dictIndexKey;

    @ApiModelProperty("字典键")
    private String dictKey;

    @ApiModelProperty("字典值")
    private String dictValue;

}
