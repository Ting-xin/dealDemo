package com.deal2u.dao;

import com.deal2u.common.JsonResult;

public interface PromoDao {
    JsonResult getAllPromo(String storeName);

    JsonResult getPromoByCate(String cate, int page, int size);
}
