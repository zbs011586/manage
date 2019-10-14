package com.manage.manage.commons;

import lombok.Data;

/**
 * @description 自定义返回参数
 * @author 张宝帅
 * @date 2019/9/1 0:21
 */

@Data
public class HttpResponse {
    private int error_code;
    private Object data;
    private static final int OK = 0;

    public HttpResponse(int error_code, Object data) {
        this.error_code = error_code;
        this.data = data;
    }

    public boolean areYouOK(){
        return this.getError_code() == 0;
    }

    public static HttpResponse OK(Object data){
        return ERROR(OK,data);
    }

    public static HttpResponse ERROR(int error_code, Object data){
        return new HttpResponse(error_code,data);
    }
}
