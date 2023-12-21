package com.nhnacademy.shoppingmall.controller.management.adminProduct;

import com.nhnacademy.shoppingmall.category.domain.Category;
import com.nhnacademy.shoppingmall.category.repository.impl.CategoryRepositoryImpl;
import com.nhnacademy.shoppingmall.category.service.CategoryService;
import com.nhnacademy.shoppingmall.category.service.impl.CategoryServiceImpl;
import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.prod_cate.domain.Prod_Cate;
import com.nhnacademy.shoppingmall.prod_cate.repository.impl.Prod_CateRepositoryImpl;
import com.nhnacademy.shoppingmall.prod_cate.service.Prod_CateService;
import com.nhnacademy.shoppingmall.prod_cate.service.impl.Prod_CateServiceImpl;
import com.nhnacademy.shoppingmall.product.domain.Product;
import com.nhnacademy.shoppingmall.product.repository.impl.ProductRepositoryImpl;
import com.nhnacademy.shoppingmall.product.service.ProductService;
import com.nhnacademy.shoppingmall.product.service.impl.ProductServiceImpl;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping(method = RequestMapping.Method.GET, value = "/admin/product.do")
public class ProductController implements BaseController {
    ProductService productService = new ProductServiceImpl(new ProductRepositoryImpl());
    Prod_CateService prodCateService = new Prod_CateServiceImpl(new Prod_CateRepositoryImpl());
    CategoryService categoryService= new CategoryServiceImpl(new CategoryRepositoryImpl());
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        Integer productId = Integer.valueOf(req.getParameter("id"));
        Product product = productService.getProduct(productId);
        req.setAttribute("product",product);
        List<Prod_Cate> prodCateList = prodCateService.getAllByProductId(productId);
        List<Category> categoryList = new ArrayList<>();
        for (Prod_Cate prodCate : prodCateList) {
            Integer categoryId = prodCate.getCategoryId();
            categoryList.add(categoryService.getCategory(categoryId));
        }
        req.setAttribute("categoryList", categoryList);

        return "shop/admin/product";
    }
}
