package com.fc.system.category.service.impl;

import com.fc.core.exception.ServiceException;
import com.fc.system.auto.entity.Category;
import com.fc.system.auto.service.CategoryAutoService;
import com.fc.system.category.model.dto.CategoryDTO;
import com.fc.system.category.model.vo.CategoryVO;
import com.fc.system.category.service.CategoryService;
import com.fc.utils.recursion.RecursionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author xwl
 * @version 1.0
 * @since 2022/6/20 11:54
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryAutoService categoryAutoService;

    @Override
    public void addCategory(CategoryDTO categoryDTO) {
        Category category = new Category();
        BeanUtils.copyProperties(categoryDTO, category);
        categoryAutoService.save(category);
    }

    @Override
    public List<CategoryVO> selectCategoryTree() {
        List<Category> list = categoryAutoService.list();
        List<CategoryVO> categoryVOS = list.stream()
                .map(p -> {
                    CategoryVO categoryVO = new CategoryVO();
                    BeanUtils.copyProperties(p, categoryVO);
                    return categoryVO;
                })
                .collect(Collectors.toList());
        return RecursionUtils.recursionConvert(categoryVOS);
    }

    @Override
    public void deleteCategory(Long id) {

        //验证该id下有无子类
        Long count = categoryAutoService.lambdaQuery()
                .eq(Category::getParentId, id)
                .count();
        if (count > 0) {
            throw new ServiceException("该分类下存在子类,请先删除子类");
        }

        categoryAutoService.removeById(id);
    }

    @Override
    public void updateCategory(Long id, CategoryDTO categoryDTO) {
        Category category = new Category();
        BeanUtils.copyProperties(categoryDTO, category);
        //不允许修改父子级关系
        category.setParentId(null);
        category.setId(id);
        categoryAutoService.lambdaUpdate()
                .update(category);
    }
}
