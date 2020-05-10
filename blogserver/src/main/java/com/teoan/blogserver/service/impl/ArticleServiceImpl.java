package com.teoan.blogserver.service.impl;

import com.teoan.blogserver.entity.Article;
import com.teoan.blogserver.entity.User;
import com.teoan.blogserver.mapper.ArticleMapper;
import com.teoan.blogserver.mapper.TagsMapper;
import com.teoan.blogserver.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.HtmlUtils;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;

/**
 * (Article)表服务实现类
 *
 * @author Toean
 * @since 2020-04-24 15:24:40
 */
@Service("articleService")
@Transactional
public class ArticleServiceImpl implements ArticleService {
    @Resource
    private ArticleMapper articleMapper;

    @Autowired
    private TagsMapper tagsMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Article getArticleById(Integer id) {
        Article article=this.articleMapper.getArticleById(id);
        articleMapper.pvIncrement(id);
        return article;
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Article> queryAllByLimit(int offset, int limit) {
        return this.articleMapper.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param article 实例对象
     * @return 实例对象
     */
    @Override
    public int addNewArticle(Article article) {
        //处理文章摘要
        if (article.getSummary() == null || "".equals(article.getSummary())) {
            //直接截取
            String stripHtml = HtmlUtils.htmlEscape(article.getHtmlcontent());
            article.setSummary(stripHtml.substring(0, stripHtml.length() > 50 ? 50 : stripHtml.length()));
        }
        if (article.getId() == -1) {
            //添加操作
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            if (article.getState() == 1) {
                //设置发表日期
                article.setPublishdate(timestamp);
            }
            article.setEdittime(timestamp);
            //设置当前用户
            article.setUid(User.getCurrentUser().getId());
            int i = articleMapper.addNewArticle(article);
            //打标签
            String[] dynamicTags = article.getDynamicTags();
            if (dynamicTags != null && dynamicTags.length > 0) {
                int tags = addTagsToArticle(dynamicTags, article.getId());
                if (tags == -1) {
                    return tags;
                }
            }
            return i;
        } else {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            if (article.getState() == 1) {
                //设置发表日期
                article.setPublishdate(timestamp);
            }
            //更新
            article.setEdittime(new Timestamp(System.currentTimeMillis()));
            int i = articleMapper.updateArticle(article);
            //修改标签
            String[] dynamicTags = article.getDynamicTags();
            if (dynamicTags != null && dynamicTags.length > 0) {
                int tags = addTagsToArticle(dynamicTags, article.getId());
                if (tags == -1) {
                    return tags;
                }
            }
            return i;
        }
    }

    private int addTagsToArticle(String[] dynamicTags, Integer aid) {
        //1.删除该文章目前所有的标签
        tagsMapper.deleteTagsByAid(aid);
        //2.将上传上来的标签全部存入数据库
        tagsMapper.saveTags(dynamicTags);
        //3.查询这些标签的id
        List<Integer> tIds = tagsMapper.getTagsIdByTagName(dynamicTags);
        //4.重新给文章设置标签
        int i = tagsMapper.saveTags2ArticleTags(tIds, aid);
        return i == dynamicTags.length ? i : -1;
    }

    /**
     * 修改数据
     *
     * @param article 实例对象
     * @return 实例对象
     */
    @Override
    public Article updateArticle(Article article) {
        this.articleMapper.updateArticle(article);
        return this.getArticleById(article.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param ids 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteArticleByIds(Integer[] ids) {
        return this.articleMapper.deleteArticleByIds(ids) > 0;
    }



    @Override
    public List<String> getCountDate(int limit) {
        return articleMapper.getCountDate(User.getCurrentUser().getId(),limit);
    }

    @Override
    public List<Integer> getPvByUid(int limit) {
        return articleMapper.getPvByUid(User.getCurrentUser().getId(),limit);
    }

    @Override
    public int updateArticleState(Integer[] aids, Integer state) {
        if (state==2){
         return articleMapper.deleteArticleByIds(aids);
        }else{
         return articleMapper.updateArticleState(aids,2);
        }
    }

    @Override
    public int restoreArticle(Integer articleId) {
        return articleMapper.updateArticleStateById(articleId,1);
    }

    @Override
    public int getArticleCountByState(Integer state, Integer uid, String keywords) {
        return articleMapper.getArticleCountByState(state,uid,keywords);
    }

    @Override
    public List<Article> getArticleByState(Integer state, Integer page, Integer count, String keywords) {
        int start = (page-1)*count;
        Integer uid=User.getCurrentUser().getId();
        return articleMapper.getArticleByState(state,start,count,uid,keywords);
    }
}