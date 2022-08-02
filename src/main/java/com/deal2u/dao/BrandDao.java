package com.deal2u.dao;

import com.deal2u.common.JsonResult;
import com.deal2u.entity.Brand;

import java.util.List;

public interface BrandDao {

    JsonResult getBrandPage(int page, int size);

    JsonResult getBrandByAbc(int page, int size);

    JsonResult findBrandLikeName(String searchText);

    JsonResult findBrandByName(String storeName);

    JsonResult getBrandByCate(String cate, int page,int size);
}
