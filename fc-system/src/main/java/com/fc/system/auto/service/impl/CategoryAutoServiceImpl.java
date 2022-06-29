package com.fc.system.auto.service.impl;

import com.fc.system.auto.entity.Category;
import com.fc.system.auto.mapper.CategoryAutoMapper;
import com.fc.system.auto.service.CategoryAutoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 分类 服务实现类
 * </p>
 *
 * @author fusw
 * @since 2022-06-29
 */
@Service
public class CategoryAutoServiceImpl extends ServiceImpl<CategoryAutoMapper, Category> implements CategoryAutoService {

}
