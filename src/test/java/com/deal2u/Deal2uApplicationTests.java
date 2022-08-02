package com.deal2u;

import com.deal2u.dao.BrandDao;
import com.deal2u.entity.Brand;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.*;

@SpringBootTest
class Deal2uApplicationTests {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    BrandDao brandDao;

    @Test
    void contextLoads() {
        String sql = "select * from store order by storeName ASC limit 50, 50";
        // List<Brand> brands = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Brand.class));
        // System.out.println();
        brandDao.findBrandByName("sjlkfjsaldfhlksahgwqheothweorjhfdowsdhflkahsdf");
        System.out.println();
        // List<Brand> brands = brandMapper.getBrand();
        // TreeMap<String, HashMap<String, HashSet<String>>> firstCate = new TreeMap<>();
        // int index = 0;
        // for (Brand item : brands) {
        //     System.out.println(++index);
        //     String cate = item.getCate();
        //     String[] split = cate.split(",");
        //     HashMap<String, HashSet<String>> secondCate = firstCate.get(split[0]);
        //     if (split.length >= 2) {
        //         if (secondCate == null) secondCate = new HashMap<>();
        //         HashSet<String> thirdCate = secondCate.get(split[1]);
        //         if (split.length >= 3) {
        //             if (thirdCate == null) thirdCate = new HashSet<>();
        //             thirdCate.add(split[2]);
        //         }
        //         if (thirdCate == null) thirdCate = new HashSet<>();
        //         secondCate.put(split[1], thirdCate);
        //     } else {
        //         //无此一级目录
        //         if (secondCate == null) secondCate = new HashMap<>();
        //         firstCate.put(split[0], secondCate);
        //     }
        //     firstCate.put(split[0], secondCate);
        // }
        //
        // File file = new File("G:\\DealStation\\Web\\cate.txt");
        // try {
        //     if (!file.exists()) file.createNewFile();
        //     FileOutputStream fs = new FileOutputStream(file);
        //
        //     for (Iterator<String> it = firstCate.keySet().iterator(); it.hasNext(); ) {
        //         String first = it.next();
        //         fs.write((first + "\r\n").getBytes(StandardCharsets.UTF_8));
        //         HashMap<String, HashSet<String>> secondCate = firstCate.get(first);
        //         for (Iterator<String> it2 = secondCate.keySet().iterator(); it2.hasNext(); ) {
        //             String second = it2.next();
        //             fs.write(("    " + second + "\r\n").getBytes(StandardCharsets.UTF_8));
        //             HashSet<String> third = secondCate.get(second);
        //             for (String item : third) {
        //                 fs.write(("        " + item + "\r\n").getBytes(StandardCharsets.UTF_8));
        //             }
        //         }
        //     }
        //     fs.flush();
        //     fs.close();
        //
        // } catch (Exception e) {
        //     e.printStackTrace();
        // }

    }

}
