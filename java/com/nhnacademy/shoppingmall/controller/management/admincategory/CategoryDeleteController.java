package com.nhnacademy.shoppingmall.controller.management.admincategory;


import com.nhnacademy.shoppingmall.category.repository.impl.CategoryRepositoryImpl;
import com.nhnacademy.shoppingmall.category.service.CategoryService;
import com.nhnacademy.shoppingmall.category.service.impl.CategoryServiceImpl;
import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping(method = RequestMapping.Method.POST, value = "/admin/category/delete.do")
public class CategoryDeleteController  implements BaseController {

    CategoryService categoryService = new CategoryServiceImpl(new CategoryRepositoryImpl());
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        Integer categoryId = Integer.valueOf(req.getParameter("id"));
        if(Objects.isNull(categoryService.getCategory(categoryId))){
            return "/error.do";
        }
        categoryService.deleteCategory(categoryId);
        return "redirect:/admin/category.do";
    }
}
