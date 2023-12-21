package com.nhnacademy.shoppingmall.prod_cate.service;

import com.nhnacademy.shoppingmall.prod_cate.domain.Prod_Cate;
import java.util.List;

public interface Prod_CateService {
    void saveProd_Cate(Prod_Cate prodCate);
    void updateProd_Cate(Prod_Cate prodCate, Integer newCategoryId);
    void deleteProd_Cate(Prod_Cate prodCate);
    List<Prod_Cate> getAllByProductId(Integer productId);
    List<Prod_Cate> getAllByCategoryId(Integer categoryId);

}
