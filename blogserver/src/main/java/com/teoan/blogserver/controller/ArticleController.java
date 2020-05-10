package com.teoan.blogserver.controller;

import com.teoan.blogserver.entity.Article;
import com.teoan.blogserver.entity.RespBean;
import com.teoan.blogserver.entity.User;
import com.teoan.blogserver.service.ArticleService;
import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * (Article)表控制层
 *
 * @author Toean
 * @since 2020-04-24 15:24:40
 */
@RestController
@RequestMapping("/article")
public class ArticleController {
    /**
     * 服务对象
     */
    @Resource
    private ArticleService articleService;

    @PostMapping(value = "/")
    public RespBean addNewArticle(@RequestBody Article article){
        int result = articleService.addNewArticle(article);
        if (result == 1) {
            return new RespBean("success", article.getId() + "");
        } else {
            return new RespBean("error", article.getState() == 0 ? "文章保存失败!" : "文章发表失败!");
        }
    }


    /**
     * 上传图片
     *
     * @return 返回图片地址
     */
    @PostMapping(value = "/uploadimg")
    public RespBean uploadImg(HttpServletRequest req, MultipartFile image) {
        StringBuffer url = new StringBuffer();
        String filePath = "/blogimg/" +  new SimpleDateFormat("yyyyMMdd").format(new Date());
        //获取文件真实路径
        //从当前servlet部署在tomcat中的文件夹算起的相对路径
        String imgFolderPath = req.getServletContext().getRealPath(filePath);
        File imgFolder = new File(imgFolderPath);
        if (!imgFolder.exists()) {
            imgFolder.mkdirs();
        }
        //生成url
        url.append(req.getScheme())
                .append("://")
                .append(req.getServerName())
                .append(":")
                .append(req.getServerPort())
                .append(req.getContextPath())
                .append(filePath);
        String imgName = UUID.randomUUID() + "_" + image.getOriginalFilename().replaceAll(" ", "");
        try {
            IOUtils.write(image.getBytes(), new FileOutputStream(new File(imgFolder, imgName)));
            url.append("/").append(imgName);
            return new RespBean("success", url.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new RespBean("error", "上传失败!");
    }

    @GetMapping("/all")
    public Map<String, Object> getArticleByState(@RequestParam(value = "state", defaultValue = "-1") Integer state, @RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "count", defaultValue = "6") Integer count, String keywords) {
        int totalCount = articleService.getArticleCountByState(state, User.getCurrentUser().getId(),keywords);
        List<Article> articles = articleService.getArticleByState(state, page, count,keywords);
        Map<String, Object> map = new HashMap<>();
        map.put("totalCount", totalCount);
        map.put("articles", articles);
        return map;
    }

    @GetMapping("/{aid}")
    public Article getArticleById(@PathVariable Integer aid) {
        return articleService.getArticleById(aid);
    }

    @PutMapping("/restore")
    public RespBean restoreArticle(Integer articleId) {
        if (articleService.restoreArticle(articleId) == 1) {
            return new RespBean("success", "还原成功!");
        }
        return new RespBean("error", "还原失败!");
    }


    @RequestMapping("/dataStatistics")
    public Map<String,Object> dataStatistics() {
        Map<String, Object> map = new HashMap<>();
        List<String> categories = articleService.getCountDate(7);
        List<Integer> dataStatistics = articleService.getPvByUid(7);
        map.put("categories", categories);
        map.put("ds", dataStatistics);
        return map;
    }

    @PutMapping("/dustbin")
    public RespBean updateArticleState(Integer[] aids,Integer state){
        if(articleService.updateArticleState(aids, state)==aids.length){
            return new RespBean("success", "删除成功!");
        }
        return new RespBean("error", "删除失败!");
    }

}