package com.fc.system.auto.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 组织机构
 * </p>
 *
 * @author xiawl
 * @since 2022-06-07
 */
@Getter
@Setter
@ApiModel(value = "Org对象", description = "组织机构")
public class Org implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("序号")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("修改时间")
    private LocalDateTime updateTime;

    @ApiModelProperty("上级机构code")
    private String parentOrgCode;

    @ApiModelProperty("机构code")
    @TableField(fill = FieldFill.INSERT)
    private String orgCode;

    @ApiModelProperty("机构名称")
    @TableField(fill = FieldFill.INSERT)
    private String orgName;

    @ApiModelProperty("0-未启用;1-已启用")
    private Integer enable;


}
