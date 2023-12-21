package com.nhnacademy.shoppingmall.controller.management.admincategory;

import com.nhnacademy.shoppingmall.category.domain.Category;
import com.nhnacademy.shoppingmall.category.exception.CategoryNotFoundException;
import com.nhnacademy.shoppingmall.category.repository.impl.CategoryRepositoryImpl;
import com.nhnacademy.shoppingmall.category.service.CategoryService;
import com.nhnacademy.shoppingmall.category.service.impl.CategoryServiceImpl;
import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping(method = RequestMapping.Method.POST, value = "/admin/category/updateAction.do")
public class CategoryUpdatePostController implements BaseController {
    CategoryService categoryService = new CategoryServiceImpl(new CategoryRepositoryImpl());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        String categoryName = req.getParameter("categoryName");
        Integer categoryId = Integer.valueOf(req.getParameter("categoryId"));
        try {
            if (req.getParameter("parentId").equals("null")) {
                categoryService.updateCategory(new Category(categoryId, categoryName, 0));
            } else {
                Integer parentId = Integer.valueOf(req.getParameter("parentId"));
                categoryService.updateCategory(new Category(categoryId,categoryName, parentId));
            }
        } catch (CategoryNotFoundException e) {
            return "/error.do";
        }
        return "redirect:/admin.do";
    }
}