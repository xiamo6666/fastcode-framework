package com.fc.utils.excel;

import com.alibaba.excel.annotation.ExcelProperty;

import javax.validation.constraints.NotNull;


/**
 * @ClassName: ExcelTestDto
 * @Description: dto
 * @Author: xwl
 * @Date: 2022/5/6 13:26
 * @Vsersion: 1.0
 */

public class ExcelTestDto {
    @ExcelProperty("name")
    @NotNull(message = "名字不能为空")
    private String name;
    @ExcelProperty("age")
    @NotNull(message = "年龄不能为空")
    private Integer age;
    @ExcelProperty("size")
    @NotNull(message = "大小不能为空")
    private Integer size;

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public Integer getSize() {
        return size;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
