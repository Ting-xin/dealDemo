package com.deal2u.service;

import com.deal2u.common.JsonResult;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public interface DealService {
    JsonResult getAllBrand(int page, int size);

    JsonResult getAllBrandAbc(int page, int size);

    JsonResult getBrandByCate(String cate, int page, int size);

    JsonResult getBrandByLikeName(String searchText);

    JsonResult getBrandByName(String storeName);


//  ==========Promo 相关内容========================
    JsonResult getPromoByBrand(String storeName);

    JsonResult getPromoByCate(String cate, int page, int size);
}
