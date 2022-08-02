package com.deal2u.service;

import com.deal2u.common.JsonResult;
import com.deal2u.dao.BrandDao;
import com.deal2u.dao.PromoDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @ClassName DealServiceImpl
 * @Description 服务
 * @Author zhngzhng
 * @Date 2022/7/21
 **/
@Service
@Slf4j
@Component
public class DealServiceImpl implements DealService {
    private final BrandDao brandDao;

    private final PromoDao promoDao;

    public DealServiceImpl(BrandDao brandDao, PromoDao promoDao) {
        this.brandDao = brandDao;
        this.promoDao = promoDao;
    }

    @Override
    public JsonResult getAllBrand(int page, int size) {
        return brandDao.getBrandPage(page, size);
    }

    @Override
    public JsonResult getAllBrandAbc(int page, int size) {
        return brandDao.getBrandByAbc(page, size);
    }

    @Override
    public JsonResult getBrandByCate(String cate, int page, int size) {
        return brandDao.getBrandByCate(cate, page, size);
    }

    @Override
    public JsonResult getBrandByLikeName(String searchText) {
        return brandDao.findBrandLikeName(searchText);
    }

    @Override
    public JsonResult getBrandByName(String storeName) {
        return brandDao.findBrandByName(storeName);
    }

    @Override
    public JsonResult getPromoByBrand(String storeName) {
        return promoDao.getAllPromo(storeName);
    }

    @Override
    public JsonResult getPromoByCate(String cate, int page, int size) {
        return promoDao.getPromoByCate(cate, page, size);
    }
}
