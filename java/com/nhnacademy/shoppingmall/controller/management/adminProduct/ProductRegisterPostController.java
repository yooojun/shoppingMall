package com.nhnacademy.shoppingmall.controller.management.adminProduct;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.prod_cate.domain.Prod_Cate;
import com.nhnacademy.shoppingmall.prod_cate.repository.impl.Prod_CateRepositoryImpl;
import com.nhnacademy.shoppingmall.prod_cate.service.Prod_CateService;
import com.nhnacademy.shoppingmall.prod_cate.service.impl.Prod_CateServiceImpl;
import com.nhnacademy.shoppingmall.product.domain.Product;
import com.nhnacademy.shoppingmall.product.exception.ProductAlreadyExistsException;
import com.nhnacademy.shoppingmall.product.repository.impl.ProductRepositoryImpl;
import com.nhnacademy.shoppingmall.product.service.ProductService;
import com.nhnacademy.shoppingmall.product.service.impl.ProductServiceImpl;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping(method = RequestMapping.Method.POST, value = "/admin/product/registerAction.do")
public class ProductRegisterPostController implements BaseController {

    private final ProductService productService = new ProductServiceImpl(new ProductRepositoryImpl());

    private final Prod_CateService prodCateService = new Prod_CateServiceImpl(new Prod_CateRepositoryImpl());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String productName = req.getParameter("productName");
        String modelNumber = req.getParameter("modelNumber");
        Integer unitCost = Integer.valueOf(req.getParameter("unitCost"));
        String description = req.getParameter("description");
        String productImage = req.getAttribute("productImage").toString();
        log.debug("productImage : {}", productImage);
        Product product = null;

        try {
            if (productImage.equals(null)) {
                product = new Product(modelNumber, productName, unitCost, description);
                productService.saveProduct(product);
            } else {
                product = new Product(modelNumber, productName, unitCost, productImage, description);
                productService.saveProduct(product);
            }
        } catch (ProductAlreadyExistsException e) {
            return "/error.do";
        }
        Integer categoryId1 = Integer.valueOf(req.getParameter("categoryId1"));
        prodCateService.saveProd_Cate(new Prod_Cate(product.getProductId(), categoryId1));
        if (!req.getParameter("categoryId2").equals("null")) {
            Integer categoryId2 = Integer.valueOf(req.getParameter("categoryId2"));
            prodCateService.saveProd_Cate(new Prod_Cate(product.getProductId(), categoryId2));
        }
        if (!req.getParameter("categoryId3").equals("null")) {
            Integer categoryId3 = Integer.valueOf(req.getParameter("categoryId3"));
            prodCateService.saveProd_Cate(new Prod_Cate(product.getProductId(), categoryId3));
        }


        return "redirect:/admin.do";

    }
}
