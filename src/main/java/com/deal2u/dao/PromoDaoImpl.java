package com.deal2u.dao;

import com.alibaba.fastjson.JSONObject;
import com.deal2u.common.JsonResult;
import com.deal2u.common.ResultUtils;
import com.deal2u.entity.Promo;
import com.deal2u.entity.PromoType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName PromoDaoImpl
 * @Description 从数据库获取 promo 信息
 * @Author zhngzhng
 * @Date 2022/7/19
 **/
@Repository
@Slf4j
public class PromoDaoImpl implements PromoDao {
    private final JdbcTemplate jdbcTemplate;

    public PromoDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public JsonResult getAllPromo(String storeName) {
        String sql = "select * from coupon_full where storeName = ?";
        try {
            // List<Promo> promoList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Promo.class));
            List<Map<String, Object>> promoList = jdbcTemplate.queryForList(sql, storeName);
            JSONObject promoJson = dealClassify(promoList);
            return ResultUtils.success(promoJson);
        }catch (Exception e){
            return ResultUtils.error(e.toString());
        }
    }

    /**
     * 按目录查询promo
     * @param cate
     * @param page
     * @param size
     * @return
     */
    @Override
    public JsonResult getPromoByCate(String cate, int page, int size) {
        String countSql = "select count(coupon_full.id) from store left join coupon_full on store.storeName = coupon_full.storeName where store.cate like '%"+ cate +"%'";
        HashMap<String, Integer> countInfo = acquirePageNum(countSql, size);
        try {
            // String sqlByCate = "select cate,type,coupon_full.storeName,type,description,code,verified,desUrl from store left join coupon_full.storeName = store.StoreName where store.cate like '%' ? '%' limit " + page * size + ", " + size;
            String sqlByCate = "select cate,type,coupon_full.storeName,type,description,code,verified,desUrl from store left join coupon_full on store.storeName = coupon_full.storeName where store.cate like '%' ? '%' limit " + page * size + ", " + size;
            List<Map<String, Object>> promoList = jdbcTemplate.queryForList(sqlByCate, cate);
            JSONObject promoJson = dealClassify(promoList);
            promoJson.put("totalPage", countInfo.get("totalPage"));
            promoJson.put("count", countInfo.get("count"));
            return ResultUtils.success(promoJson);
        }catch (Exception e) {
            return ResultUtils.error();
        }
    }

    private HashMap<String, Integer> acquirePageNum(String countSql, int size){
        Integer totalCount = jdbcTemplate.queryForObject(countSql, Integer.class);
        Integer totalPage = totalCount % size == 0 ? totalCount / size : totalCount / size + 1;
        HashMap<String, Integer> result = new HashMap<>();
        result.put("count", totalCount);
        result.put("totalPage", totalPage);
        return result;
    }

    private JSONObject dealClassify(List<Map<String, Object>> queryResult) {
        ArrayList<Object> deals = new ArrayList<>();
        ArrayList<Object> coupons = new ArrayList<>();
        for (Map<String, Object> item : queryResult) {
            String type = (String)item.get("type");
            if (type.equals("COUPON")) coupons.add(item);
            else deals.add(item);
        }
        JSONObject promoJson = new JSONObject();
        promoJson.put("deal", deals);
        promoJson.put("coupon", coupons);
        return promoJson;
    }
}
