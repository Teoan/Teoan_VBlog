package com.teoan.blogserver.controller;

import com.teoan.blogserver.entity.Category;
import com.teoan.blogserver.entity.RespBean;
import com.teoan.blogserver.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Category)表控制层
 *
 * @author Teoan
 * @since 2020-04-24 15:30:05
 */
@RestController
@RequestMapping("/admin/category")
public class CategoryController {
    /**
     * 服务对象
     */
    @Resource
    private CategoryService categoryService;

    @GetMapping("/all")
    public List<Category> getAllCategories(){
        return categoryService.getAllCategories();
    }

    @DeleteMapping("/{ids}")
    public RespBean deleteCategoryById(@PathVariable("ids")String ids){
        int result=categoryService.deleteCategoryByIds(ids);
        if(result>0){
            return new RespBean("success","删除成功");
        }
        return new RespBean("error","删除失败");
    }

    @PostMapping("/")
    public RespBean addCategory(@RequestBody Category category){
        if("".equals(category.getCatename())||null==category.getCatename()){
            return new RespBean("error","请输入栏目名称");
        }
        if(categoryService.addCategory(category)==1){
            return new RespBean("success","添加成功！");
        }

        return new RespBean("error","添加失败！");
    }

    @PutMapping("/")
    public RespBean updateCategory(Category category){
        if(categoryService.updateCategory(category)==1){
            return new RespBean("success","更新成功！");
        }
        return new RespBean("error","更新失败！");
    }


}