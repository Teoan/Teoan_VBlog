package com.teoan.blogserver.mapper;

import com.teoan.blogserver.entity.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (Article)表数据库访问层
 *
 * @author Toean
 * @since 2020-04-24 15:24:40
 */
@Mapper
public interface ArticleMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Article getArticleById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Article> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param article 实例对象
     * @return 对象列表
     */
    List<Article> queryAll(Article article);

    /**
     * 新增数据
     *
     * @param article 实例对象
     * @return 影响行数
     */
    int addNewArticle(Article article);

    /**
     * 修改数据
     *
     * @param article 实例对象
     * @return 影响行数
     */
    int updateArticle(Article article);

    /**
     * 通过主键删除数据
     *
     * @param adis 主键列表
     * @return 影响行数
     */
    int deleteArticleByIds(@Param("aids") Integer[] adis);


    /**
     * 根据状态获取文章
     *
     * @param state 状态
     * @param start 开始页数
     * @param count 数量
     * @param uid 用户id
     * @param keywords 关键词
     * @return 文章列表
     */
    List<Article> getArticleByState(@Param("state") Integer state, @Param("start") Integer start, @Param("count") Integer count, @Param("uid") Integer uid,@Param("keywords") String keywords);


    /**
     * 批量更新文章状态
     *
     * @param aids 文章id列表
     * @param state 文章状态
     * @return
     */
    int updateArticleState(@Param("aids")Integer aids[], @Param("state") Integer state);

    /**
     * 根据文章id更新文章状态
     *
     * @param articleId 文章id
     * @return
     */
    int updateArticleStateById(@Param("articleId") Integer articleId, @Param("state") Integer state);



    /**
     * 增加浏览量
     *
     * @param aid 文章id
     */
    void pvIncrement(Integer aid);

    /**
     * 查询统计时间
     *
     * @param uid 用户id
     * @param limit 数据量
     * @return 统计时间
     */
    List<String> getCountDate(@Param("uid")Integer uid,@Param("limit")int limit);

    /**
     * 查询浏览量
     *
     * @param uid 用户id
     * @return 数据量
     */
    List<Integer> getPvByUid(@Param("uid")Integer uid,@Param("limit")int limit);


    /**
     * 根据状态统计文章数量
     *
     * @param state 状态
     * @param uid 用户id
     * @param keywords 关键词
     * @return 文章数量
     */
    int getArticleCountByState(@Param("state") Integer state, @Param("uid") Integer uid, @Param("keywords") String keywords);
}