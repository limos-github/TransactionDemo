package com.limo.transactiondemo.exception;

/**
 * @description:
 * @author: Limo
 * @time: 2022/9/3 14:27
 */
public class BusinessException extends RuntimeException {
    private Integer code;
    private String msg;

    public BusinessException(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}