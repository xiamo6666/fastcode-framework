package com.fc.system.category.service;

import com.fc.system.category.model.dto.CategoryDTO;
import com.fc.system.category.model.vo.CategoryVO;

import java.util.List;

/**
 * @author xwl
 * @version 1.0
 * @since 2022/6/20 11:40
 */
public interface CategoryService {
    void addCategory(CategoryDTO categoryDTO);

    List<CategoryVO> selectCategoryTree();


    void deleteCategory(Long id);


    void updateCategory(Long id, CategoryDTO categoryDTO);
}
