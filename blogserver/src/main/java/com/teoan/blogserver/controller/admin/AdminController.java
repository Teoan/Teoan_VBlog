package com.teoan.blogserver.controller.admin;

import com.teoan.blogserver.entity.Article;
import com.teoan.blogserver.entity.RespBean;
import com.teoan.blogserver.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Teoan
 * @description
 * @date 2020/5/1 15:59
 */
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    ArticleService articleService;


    @GetMapping("/article/all")
    public Map<String, Object> getArticleByStateByAdmin(@RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "count", defaultValue = "6") Integer count, String keywords) {
        List<Article> articles = articleService.getArticleByState(-2, page, count, keywords);
        Map<String, Object> map = new HashMap<>();
        map.put("articles", articles);
        map.put("totalCount", articleService.getArticleCountByState(1, null, keywords));
        return map;
    }

    @PutMapping("/article/dustbin")
    public RespBean updateArticleState(Integer[] aids, Integer state) {
        if (articleService.updateArticleState(aids, state) == aids.length) {
            return new RespBean("success", "删除成功!");
        }
        return new RespBean("error", "删除失败!");
    }
}
