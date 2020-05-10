package com.teoan.blogserver.mapper;

import com.teoan.blogserver.entity.Tags;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (Tags)表数据库访问层
 *
 * @author Toean
 * @since 2020-04-24 15:33:44
 */
@Mapper
public interface TagsMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Tags queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Tags> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param tags 实例对象
     * @return 对象列表
     */
    List<Tags> queryAll(Tags tags);

    /**
     * 新增数据
     *
     * @param tags 实例对象
     * @return 影响行数
     */
    int insert(Tags tags);

    /**
     * 修改数据
     *
     * @param tags 实例对象
     * @return 影响行数
     */
    int update(Tags tags);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

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