package com.deal2u.controller;

import com.deal2u.common.JsonResult;
import com.deal2u.service.DealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName PromoController
 * @Description 优惠码相关接口
 * @Author zhngzhng
 * @Date 2022/7/21
 **/
@RestController
@RequestMapping(value = "/promo")
public class PromoController {
    private final DealService dealService;

    public PromoController(DealService dealService) {
        this.dealService = dealService;
    }

    @RequestMapping(value = "/{brand}", method = RequestMethod.GET)
    public JsonResult getBrandPromo(@PathVariable String brand) {
        return dealService.getPromoByBrand(brand);
    }

    @RequestMapping(value = "/{cate}/{page}/{size}", method = RequestMethod.GET)
    public JsonResult getBrandByCate(@PathVariable String cate, @PathVariable int page, @PathVariable int size) {
        return dealService.getPromoByCate(cate, page, size);
    }
}
