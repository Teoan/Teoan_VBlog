package com.teoan.blogserver.service;

import com.teoan.blogserver.entity.Category;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (Category)表服务接口
 *
 * @author Toean
 * @since 2020-04-24 15:30:05
 */
public interface CategoryService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Category queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Category> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param category 实例对象
     * @return 实例对象
     */
    int addCategory(Category category);

    /**
     * 修改数据
     *
     * @param category 实例对象
     * @return 实例对象
     */
    int updateCategory(Category category);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);


    /**
     * 通过id列表批量删除
     *
     * @param ids id列表
     * @return 影响行数
     */
    int deleteCategoryByIds(String ids);


    /**
     * 通过实体作为筛选条件查询
     *
     * @return 对象列表
     */
    List<Category> getAllCategories();

}