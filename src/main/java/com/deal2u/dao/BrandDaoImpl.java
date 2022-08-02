package com.deal2u.dao;

import com.alibaba.fastjson.JSONObject;
import com.deal2u.common.JsonResult;
import com.deal2u.common.ResultUtils;
import com.deal2u.entity.Brand;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName BrandDaoImpl
 * @Description Todo
 * @Author zhngzhng
 * @Date 2022/7/19
 **/
@Repository
public class BrandDaoImpl implements BrandDao{
    private final JdbcTemplate jdbcTemplate;

    public BrandDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private HashMap<String, Integer> acquirePageNum(String countSql, int size){
        Integer totalCount = jdbcTemplate.queryForObject(countSql, Integer.class);
        Integer totalPage = totalCount % size == 0 ? totalCount / size : totalCount / size + 1;
        HashMap<String, Integer> result = new HashMap<>();
        result.put("count", totalCount);
        result.put("totalPage", totalPage);
        return result;
    }

    @Override
    public JsonResult getBrandPage(int page, int size) {
        HashMap<String, Integer> brandPageInfo = acquirePageNum("select count(id) from store", size);
        String pagedSql = "select storeName,logo,url from store where id > " + page*size + " order by id ASC limit " + size;
        List<Map<String, Object>> brandData = jdbcTemplate.queryForList(pagedSql);
        JSONObject resultJson = new JSONObject();
        resultJson.put("totalPage", brandPageInfo.get("totalPage"));
        resultJson.put("count", brandPageInfo.get("count"));
        resultJson.put("data", brandData);
        return ResultUtils.success(resultJson);
    }

    @Override
    public JsonResult getBrandByAbc(int page, int size) {
        String abcSql = "select storeName,logo,url from store order by storeName ASC limit " + page * size + ", " + size;
        HashMap<String, Integer> brandPageInfo = acquirePageNum("select count(id) from store", size);
        JSONObject resultJson = new JSONObject();
        resultJson.put("totalPage", brandPageInfo.get("totalPage"));
        resultJson.put("count", brandPageInfo.get("count"));

        List<Map<String, Object>> abcBrandData = jdbcTemplate.queryForList(abcSql);
        resultJson.put("data", abcBrandData);
        return ResultUtils.success(resultJson);
    }

    @Override
    public JsonResult findBrandLikeName(String searchText) {
        //只返回前50个，给搜索框使用
        String abcSql = "select storeName,url from store where storeName like ? '%' limit 50";
        try {
            List<Map<String, Object>> searchResult = jdbcTemplate.queryForList(abcSql, searchText);
            return ResultUtils.success(searchResult);
        }catch (EmptyResultDataAccessException e) {
            return ResultUtils.noObject("No such brand.");
        }
    }

    @Override
    public JsonResult findBrandByName(String storeName) {
        String sql = "select * from store where storeName = ?";
        try {
            Map<String, Object> brand = jdbcTemplate.queryForMap(sql, storeName);
            return ResultUtils.success(brand);
        }catch (EmptyResultDataAccessException e) {
            return ResultUtils.noObject("No such brand.");
        }
    }

    /**
     *
     * @param cate
     * @param page
     * @param size
     * @return
     */
    @Override
    public JsonResult getBrandByCate(String cate, int page, int size) {
        String decodeCate = cate.replaceAll("'","''");
        decodeCate = decodeCate.replaceAll("/", "//");
        decodeCate = decodeCate.replaceAll("%", "/%");
        decodeCate = decodeCate.replaceAll("_", "/_");

        String countSql = "select count(id) from store where cate like '%" + decodeCate + "%'";
        HashMap<String, Integer> pagedInfo = acquirePageNum(countSql, size);
        try {
            String cateSql = "select * from store where cate like '%' ? '%' limit " + page * size + "," + size;
            List<Map<String, Object>> cateResult = jdbcTemplate.queryForList(cateSql, cate);
            JSONObject resultJson = new JSONObject();
            resultJson.put("totalPage", pagedInfo.get("totalPage"));
            resultJson.put("count", pagedInfo.get("count"));
            resultJson.put("data", cateResult);
            return ResultUtils.success(resultJson);
        }catch (EmptyResultDataAccessException e){
            return ResultUtils.noObject("no such brand.");
        }
    }
}
