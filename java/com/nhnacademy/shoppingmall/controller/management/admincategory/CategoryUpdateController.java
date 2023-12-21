package com.nhnacademy.shoppingmall.controller.management.admincategory;

import com.nhnacademy.shoppingmall.category.domain.Category;
import com.nhnacademy.shoppingmall.category.repository.impl.CategoryRepositoryImpl;
import com.nhnacademy.shoppingmall.category.service.CategoryService;
import com.nhnacademy.shoppingmall.category.service.impl.CategoryServiceImpl;
import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping(method = RequestMapping.Method.GET, value = "/admin/category/update.do")
public class CategoryUpdateController implements BaseController {
    CategoryService categoryService = new CategoryServiceImpl(new CategoryRepositoryImpl());
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        Integer categoryId = Integer.valueOf(req.getParameter("id"));
        Category category = categoryService.getCategory(categoryId);
        req.setAttribute("oldCategory", category);
        List<Category> categoryList = categoryService.findAll();
//        categoryList.remove(category);
        req.setAttribute("categoryList",categoryList);

        return "shop/admin/categoryupdate";
    }
}
