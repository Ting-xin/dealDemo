package com.deal2u.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName Promo
 * @Description 促销实体
 * @Author zhngzhng
 * @Date 2022/7/5
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Promo {

    private Integer id;
    private String title;
    private String description;
    //brandName
    private String storeName;
    //COUPON/DEAL
    private PromoType type;
    //优惠码,COUPON才有code,DEAL类型无码
    private String code;
    //优惠是否验证，true为验证，false未验证
    private Boolean verified;
    //跳转链接,一般DEAL会有，直接跳转到DEAL的网址；部分COUPON也有
    private String desUrl;
}
