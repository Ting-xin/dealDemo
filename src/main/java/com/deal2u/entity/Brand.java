package com.deal2u.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

/**
 * @ClassName Brand
 * @Description Entity
 * @Author zhngzhng
 * @Date 2022/7/13
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Brand {
    Integer id;
    String storeName;
    //brand website
    String url;
    //logo file path
    String logo;
    String description;
    //三级目录用逗号分割
    String cate;

}
