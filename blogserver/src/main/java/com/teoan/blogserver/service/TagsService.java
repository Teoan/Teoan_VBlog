package com.teoan.blogserver.service;

import com.teoan.blogserver.entity.Tags;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (Tags)表服务接口
 *
 * @author Toean
 * @since 2020-04-24 15:33:44
 */
public interface TagsService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Tags queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Tags> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param tags 实例对象
     * @return 实例对象
     */
    Tags insert(Tags tags);

    /**
     * 修改数据
     *
     * @param tags 实例对象
     * @return 实例对象
     */
    Tags update(Tags tags);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);


    /**
     * 通过文章id删除标签
     *
     * @param id 文章id
     * @return 影响行数
     */
    int deleteTagsByAid(Integer id);


    /**
     * 保存标签
     *
     * @param tags 标签列表
     * @return 影响行数
     */
    int saveTags(@Param("tags") String[] tags);

    /**
     * 根据标签名获取标签id
     *
     * @param tagNames 标签名字列表
     * @return 标签id
     */
    List<Integer> getTagsIdByTagName(@Param("tagNames") String[] tagNames);


    /**
     * 保存标签到文章中
     *
     * @param tagIds 标签id
     * @param aid 文章id
     * @return 影响行数
     */
    int saveTags2ArticleTags(@Param("tagIds") List<Integer> tagIds, @Param("aid") Integer aid);

}