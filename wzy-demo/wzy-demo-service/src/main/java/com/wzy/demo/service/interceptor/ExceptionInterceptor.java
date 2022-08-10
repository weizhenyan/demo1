package com.wzy.demo.service.interceptor;

import com.wzy.demo.service.api.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpMediaTypeException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

/**
 * ExceptionInterceptor
 *
 * @author 飞燕
 * @date   2022/8/9 11:34
 */
@Component
@ControllerAdvice
@ResponseBody
@Slf4j
public class ExceptionInterceptor {


    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result<?> exceptionHandler(HttpServletRequest req, Exception e) {
        log.info("调用异常", req.getRequestURI());
        Result<Object> result = new Result<Object>();
        Throwable throwable = e.getCause();
        if (null == throwable) {
            throwable = e;
        }

        if (e instanceof SQLException) {
            SQLException sqlException = (SQLException) e;
            result.setCode(9888);
            result.setMessage("sql异常" + sqlException.getSQLState());
            return result;
        } else if (e instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException error = (MethodArgumentNotValidException) e;
            result.setCode(9111);
            result.setMessage(error.getBindingResult().getFieldErrors().get(0).getDefaultMessage());
            return result;
        } else if (e instanceof MethodArgumentTypeMismatchException) {
            result.setCode(9110);
            result.setMessage("参数类型错误,请传入正确参数类型");
            return result;
        } else if (e instanceof HttpMediaTypeException) {
            result.setCode(9120);
            result.setMessage("参数传递错误,请传入正确参数");
            return result;
        } else if (e instanceof HttpRequestMethodNotSupportedException) {
            result.setCode(9120);
            result.setMessage("接口解析异常,请正确传参");
            return result;
        } else {
            result.setCode(9500);
            result.setMessage("未知系统异常");
            return result;
        }
    }
}
