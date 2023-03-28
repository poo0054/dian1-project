package com.dian1.gen.entity;

import lombok.Data;

/**
 * @author zhangzhi
 * @date 2023/3/17
 */
@Data
public class ResponseResult<T> {
    private String status = "200";
    private String msg;
    private String errorMsg;
    private T data;

    public ResponseResult(T data) {
        this.data = data;
    }

    public ResponseResult() {
    }
}
