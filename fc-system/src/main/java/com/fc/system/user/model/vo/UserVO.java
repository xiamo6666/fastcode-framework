package com.fc.system.user.model.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author xwl
 * @version 1.0
 * @since 2022/6/8 14:40
 */
@Data
public class UserVO  {
    @ApiModelProperty("序号")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("姓名")
    private String fullName;

    @ApiModelProperty("组织机构code")
    @TableField(fill = FieldFill.INSERT)
    private String orgCode;

    @ApiModelProperty("组织机构名称")
    @TableField(fill = FieldFill.INSERT)
    private String orgName;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("修改时间")
    private LocalDateTime updateTime;

    @ApiModelProperty("备注")
    private String remark;
}
