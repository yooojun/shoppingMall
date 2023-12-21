package com.nhnacademy.shoppingmall.prod_cate.service.impl;

import com.nhnacademy.shoppingmall.prod_cate.domain.Prod_Cate;
import com.nhnacademy.shoppingmall.prod_cate.exception.Prod_CateAlreadyExistsException;
import com.nhnacademy.shoppingmall.prod_cate.exception.Prod_CateMaxOverException;
import com.nhnacademy.shoppingmall.prod_cate.exception.Prod_CateNotFoundException;
import com.nhnacademy.shoppingmall.prod_cate.repository.Prod_CateRepository;
import com.nhnacademy.shoppingmall.prod_cate.repository.impl.Prod_CateRepositoryImpl;
import com.nhnacademy.shoppingmall.prod_cate.service.Prod_CateService;
import java.util.List;

public class Prod_CateServiceImpl implements Prod_CateService {

    Prod_CateRepository prod_cateRepository;

    public Prod_CateServiceImpl(Prod_CateRepository prod_cateRepository) {
        this.prod_cateRepository = prod_cateRepository;
    }

    @Override
    public void saveProd_Cate(Prod_Cate prodCate) {
        if (prod_cateRepository.countByProductId(prodCate.getProductId()) > 3) {
            throw new Prod_CateMaxOverException(prodCate.getProductId());
        } else if (prod_cateRepository.countProd_Cate(prodCate) > 0) {
            throw new Prod_CateAlreadyExistsException(prodCate);
        }
        int result = prod_cateRepository.save(prodCate);
        if (result < 1) {
            throw new RuntimeException("save fail");
        }
    }

    @Override
    public void updateProd_Cate(Prod_Cate prodCate, Integer newCategoryId) {
        if (prod_cateRepository.countProd_Cate(prodCate) < 1) {
            throw new Prod_CateNotFoundException(prodCate);
        }
        int result = prod_cateRepository.update(prodCate, newCategoryId);
        if (result < 1) {
            throw new RuntimeException("update fail");
        }

    }

    @Override
    public void deleteProd_Cate(Prod_Cate prodCate) {
        if (prod_cateRepository.countProd_Cate(prodCate) < 1) {
            throw new Prod_CateNotFoundException(prodCate);
        }
        int result = prod_cateRepository.deleteByProd_Cate(prodCate);
        if(result<1){
            throw new RuntimeException("delete fail");
        }

    }

    @Override
    public List<Prod_Cate> getAllByProductId(Integer productId) {
        List<Prod_Cate> prod_cateList = prod_cateRepository.findByProductId(productId);
        if(prod_cateList.isEmpty()){
            return null;
        }
        return prod_cateList;
    }

    @Override
    public List<Prod_Cate> getAllByCategoryId(Integer categoryId) {
        List<Prod_Cate> prod_cateList = prod_cateRepository.findByProductId(categoryId);
        if(prod_cateList.isEmpty()){
            return null;
        }
        return prod_cateList;
    }
}
