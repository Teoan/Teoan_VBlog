package com.teoan.blogserver.service.impl;

import com.teoan.blogserver.entity.Category;
import com.teoan.blogserver.mapper.CategoryMapper;
import com.teoan.blogserver.service.CategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;

/**
 * (Category)表服务实现类
 *
 * @author Toean
 * @since 2020-04-24 15:30:05
 */
@Service("categoryService")
@Transactional
public class CategoryServiceImpl implements CategoryService {
    @Resource
    private CategoryMapper categoryMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Category queryById(Integer id) {
        return this.categoryMapper.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Category> queryAllByLimit(int offset, int limit) {
        return this.categoryMapper.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param category 实例对象
     * @return 实例对象
     */
    @Override
    public int addCategory(Category category) {
        category.setDate(new Timestamp(System.currentTimeMillis()));
        return this.categoryMapper.addCategory(category);
    }

    /**
     * 修改数据
     *
     * @param category 实例对象
     * @return 实例对象
     */
    @Override
    public int updateCategory(Category category) {
        return this.categoryMapper.updateCategory(category);
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.categoryMapper.deleteById(id) > 0;
    }

    @Override
    public int deleteCategoryByIds(String ids) {
        String[] split=ids.split(",");
        return categoryMapper.deleteCategoryByIds(split);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryMapper.getAllCategories();
    }
}