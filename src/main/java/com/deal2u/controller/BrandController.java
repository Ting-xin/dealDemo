package com.deal2u.controller;

import com.deal2u.common.JsonResult;
import com.deal2u.service.DealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName BrandController
 * @Description brand的相关接口
 * @Author zhngzhng
 * @Date 2022/7/21
 **/
@RestController
@RequestMapping(value = "/brand")
public class BrandController {
    private final DealService dealService;

    public BrandController(DealService dealService) {
        this.dealService = dealService;
    }

    @RequestMapping(value = "/{page}/{size}", method = RequestMethod.GET)
    public JsonResult getAllBrand(@PathVariable int page, @PathVariable int size){
        return dealService.getAllBrand(page, size);
    }

    @RequestMapping(value = "/{page}/{size}/{orderType}", method = RequestMethod.GET)
    public JsonResult getAllBrandByOrderType(@PathVariable int page, @PathVariable int size, @PathVariable String orderType){
       return dealService.getAllBrandAbc(page, size);
    }

    @RequestMapping(value = "/like/{searchText}", method = RequestMethod.GET)
    public JsonResult findBrandLikeName(@PathVariable String searchText) {
        return dealService.getBrandByLikeName(searchText);
    }

    @RequestMapping(value = "/{storeName}", method = RequestMethod.GET)
    public JsonResult finBrandByStoreName(@PathVariable String storeName) {
        return dealService.getBrandByName(storeName);
    }

    @RequestMapping(value = "/cate/{cate}/{page}/{size}", method = RequestMethod.GET)
    public JsonResult findBrandByCate(@PathVariable String cate, @PathVariable int page, @PathVariable int size){
        return dealService.getBrandByCate(cate, page, size);
    }

}
