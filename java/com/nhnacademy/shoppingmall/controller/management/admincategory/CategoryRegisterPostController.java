package com.nhnacademy.shoppingmall.controller.management.admincategory;

import com.nhnacademy.shoppingmall.category.domain.Category;
import com.nhnacademy.shoppingmall.category.exception.CategoryAlreadyExistsException;
import com.nhnacademy.shoppingmall.category.repository.impl.CategoryRepositoryImpl;
import com.nhnacademy.shoppingmall.category.service.CategoryService;
import com.nhnacademy.shoppingmall.category.service.impl.CategoryServiceImpl;
import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping(method = RequestMapping.Method.POST, value = "/admin/category/registerAction.do")
public class CategoryRegisterPostController implements BaseController {

    CategoryService categoryService = new CategoryServiceImpl(new CategoryRepositoryImpl());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String categoryName = req.getParameter("categoryName");
        try {
            if (req.getParameter("parentId").equals("null")) {
                categoryService.saveCategory(new Category(categoryName));
            } else {
                Integer parentId = Integer.valueOf(req.getParameter("parentId"));
                categoryService.saveCategory(new Category(categoryName, parentId));
            }
        } catch (CategoryAlreadyExistsException e){
            return "/error.do";
        }
        return "redirect:/admin.do";
    }
}
