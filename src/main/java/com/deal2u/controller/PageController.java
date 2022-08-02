package com.deal2u.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.deal2u.common.JsonResult;
import com.deal2u.service.CommonService;
import com.deal2u.service.DealService;
import com.deal2u.service.DealServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Controller
public class PageController {
    private final DealService dealService;

    public PageController(DealService dealService) {
        this.dealService = dealService;
    }

    static final String VIEW_INDEX = "mall/index";
    static final String VIEW_BRAND = "mall/brand";
    static final String VIEW_CATE = "mall/category";
    static final String VIEW_DETAIL = "mall/brand_detail";

    static final String temp = "mall/temp";


    @GetMapping(value = {"" , "index"})
    public String getHome() {
        return VIEW_INDEX;
    }

    @GetMapping(value = "brand")
    public ModelAndView getViewBrand(@RequestParam(value = "page", required = false) Integer page,
                                     @RequestParam(value = "size", required = false) Integer size,
                                     @RequestParam(value = "orderType", required = false) String orderType) {
        ModelAndView modelAndView = new ModelAndView(VIEW_BRAND);
        if(page == null || !CommonService.isInteger(page)) {
            page = 1;
        }
        if(size == null || !CommonService.isInteger(size)) {
            size = 10;
        }
        Map<String, String> map = new HashMap<>();
        map.put("Popularity", "a");
        map.put("Rating", "b");
        map.put("Alphabetical", "c");
        JsonResult jsonResult;
        if(map.containsKey(orderType)) {
            if(orderType.equals("Popularity") || orderType.equals("Rating")) {
                jsonResult = dealService.getAllBrand(page -1, size);
            } else {
                jsonResult = dealService.getAllBrandAbc(page - 1, size);
            }
        } else {
            jsonResult = dealService.getAllBrand(page -1, size);
        }
        // JsonResult jsonResult = dealService.getAllBrandAbc(page - 1, size);
        Map tempMap = (Map)jsonResult.getData();
        modelAndView.addObject("brands", (ArrayList)tempMap.get("data"));
        return modelAndView;
    }



    @GetMapping(value = "brand_detail")
    public ModelAndView getViewCoupon(@RequestParam(value = "brandName", required = true) String name) {
        ModelAndView modelAndView = new ModelAndView(VIEW_DETAIL);
        return modelAndView;
    }

    @GetMapping(value = "category")
    public ModelAndView getViewCategory(@RequestParam(value = "page", required = false) Integer page,
                                        @RequestParam(value = "size", required = false) Integer size,
                                        @RequestParam(value = "category", required = false) String category,
                                        @RequestParam(value = "subCategory", required = false) String cate) {
        ModelAndView modelAndView = new ModelAndView(VIEW_CATE);
        if(page == null || !CommonService.isInteger(page)) {
            page = 0;
        } else if(page > 0){
            page -= 1;
        }
        if(size == null || !CommonService.isInteger(size)) {
            size = 10;
        }
        String finalCate;
        if(!CommonService.isCategory(category)) {
            finalCate = "Apparel";
        } else {
            finalCate = cate;
        }
        JsonResult jsonResult = dealService.getBrandByCate(finalCate, page, size);
        Map tempMap = (Map)jsonResult.getData();
        modelAndView.addObject("brands", (ArrayList)tempMap.get("data"));
        return modelAndView;
    }

    @GetMapping(value = "temp")
    public String getViewTemp() {return "mall/temp";}
}
