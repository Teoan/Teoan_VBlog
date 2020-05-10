package com.teoan.blogserver.service.impl;

import com.teoan.blogserver.entity.Tags;
import com.teoan.blogserver.mapper.TagsMapper;
import com.teoan.blogserver.service.TagsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Tags)表服务实现类
 *
 * @author Toean
 * @since 2020-04-24 15:33:45
 */
@Service("tagsService")
@Transactional
public class TagsServiceImpl implements TagsService {
    @Resource
    private TagsMapper tagsMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Tags queryById(Integer id) {
        return this.tagsMapper.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Tags> queryAllByLimit(int offset, int limit) {
        return this.tagsMapper.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param tags 实例对象
     * @return 实例对象
     */
    @Override
    public Tags insert(Tags tags) {
        this.tagsMapper.insert(tags);
        return tags;
    }

    /**
     * 修改数据
     *
     * @param tags 实例对象
     * @return 实例对象
     */
    @Override
    public Tags update(Tags tags) {
        this.tagsMapper.update(tags);
        return this.queryById(tags.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.tagsMapper.deleteById(id) > 0;
    }



    @Override
    public int deleteTagsByAid(Integer id) {
        return tagsMapper.deleteTagsByAid(id);
    }

    @Override
    public int saveTags(String[] tags) {
        return tagsMapper.saveTags(tags);
    }

    @Override
    public List<Integer> getTagsIdByTagName(String[] tagNames) {
        return tagsMapper.getTagsIdByTagName(tagNames);
    }

    @Override
    public int saveTags2ArticleTags(List<Integer> tagIds, Integer aid) {
        return tagsMapper.saveTags2ArticleTags(tagIds,aid);
    }
}