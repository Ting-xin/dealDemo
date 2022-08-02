package com.deal2u.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName JsonResult
 * @Description Todo
 * @Author zhngzhng
 * @Date 2022/7/21
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JsonResult<T> {
    private Integer code;

    private String msg;

    private T data;
}
