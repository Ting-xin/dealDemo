package com.deal2u.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

@Service
@Slf4j
@Component
public class CommonService {
    /**
     * 查看一个字符串是否可以转换为数字
     * @param str 字符串
     * @return true 可以; false 不可以
     */
    public static boolean isStr2Num(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isInteger(Integer num) {
        return num.toString().matches("[0-9]+$");
    }

    private static Set<String> set = new HashSet<>((Arrays.asList("Apparel", "Beauty", "Electronics", "Health", "Home", "Money", "Places & Travel", "Software")));
    public static boolean isCategory(String str) {
        return set.contains(str);
    }
}
