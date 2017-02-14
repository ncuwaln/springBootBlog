package com.blog.error;

/**
 * Created by Administrator on 2017/2/11.
 */
public class UserDefinedException extends Exception{
    private String message;
    private Integer code;

    public UserDefinedException(String message, Integer code){
        this.message = message;
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
