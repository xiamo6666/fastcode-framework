package com.fc.system.user.model.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author xwl
 * @version 1.0
 * @since 2022/6/8 14:40
 */
@Data
public class UserVO  {
    @Schema(description ="序号")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @Schema(description ="用户名")
    private String username;

    @Schema(description ="姓名")
    private String fullName;

    @Schema(description ="组织机构code")
    @TableField(fill = FieldFill.INSERT)
    private String orgCode;

    @Schema(description ="组织机构名称")
    @TableField(fill = FieldFill.INSERT)
    private String orgName;

    @Schema(description ="创建时间")
    private LocalDateTime createTime;

    @Schema(description ="修改时间")
    private LocalDateTime updateTime;

    @Schema(description ="备注")
    private String remark;
}
