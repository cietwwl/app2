package com.chuangyou.xianni.http;

import java.util.Map;

/**
 * @author Lifeix
 *
 */
public interface BaseRespone {
    public String getResult(Map<String,String> params) throws Exception;
}
