package com.fc.system.category.controller;

import com.fc.common.model.Result;
import com.fc.system.category.model.dto.CategoryDTO;
import com.fc.system.category.model.vo.CategoryVO;
import com.fc.system.category.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author xwl
 * @version 1.0
 * @since 2022/6/20 11:34
 */
@RestController
@RequestMapping("/category")
@Tag(name = "分类-树")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/addCategory")
    @Operation(summary = "添加分类")
    public Result<String> addCategory(@Validated @RequestBody CategoryDTO categoryDTO) {
        categoryService.addCategory(categoryDTO);
        return Result.success();
    }

    @GetMapping("/selectCategoryTree")
    @Operation(summary = "查询分类-树状结构")
    public Result<List<CategoryVO>> selectCategoryTree() {
        return Result.success(categoryService.selectCategoryTree());
    }

    @PostMapping("/deleteCategory/{id}")
    @Operation(summary = "删除分类")
    public Result<List<CategoryVO>> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return Result.success();
    }

    @PostMapping ("/updateCategory/{id}")
    @Operation(summary = "修改分类")
    public Result<String> updateCategory(@PathVariable Long id, @Validated @RequestBody CategoryDTO categoryDTO) {
        categoryService.updateCategory(id, categoryDTO);
        return Result.success();
    }
}
