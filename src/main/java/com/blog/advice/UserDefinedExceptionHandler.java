package com.blog.advice;

import com.blog.error.ErrorInfo;
import com.blog.error.UserDefinedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2017/2/11.
 */
@ControllerAdvice
public class UserDefinedExceptionHandler {
    @ExceptionHandler(value = UserDefinedException.class)
    @ResponseBody
    public ErrorInfo jsonErrorHandle(UserDefinedException ex){
        ErrorInfo errorInfo = new ErrorInfo();
        errorInfo.setCode(ex.getCode());
        errorInfo.setMessage(ex.getMessage());
        return errorInfo;
    }
}
