package com.fc.system.auto.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 字典信息
 * </p>
 *
 * @author xiawl
 * @since 2022-06-07
 */
@Getter
@Setter
@TableName("dict_info")
@ApiModel(value = "DictInfo对象", description = "字典信息")
public class DictInfo implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("序号")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("修改时间")
    private LocalDateTime updateTime;

    @ApiModelProperty("字典键")
    private String dictKey;

    @ApiModelProperty("字典值")
    private String dictValue;

    @ApiModelProperty("字典索引值")
    private String dictIndexKey;


}
