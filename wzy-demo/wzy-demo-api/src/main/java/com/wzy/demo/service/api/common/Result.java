package com.wzy.demo.service.api.common;

import com.wzy.demo.service.api.eum.ResultCodeEnum;
import io.swagger.annotations.ApiModel;

import java.io.Serializable;

/**
 *@belongProject:wzy-demo
 *@belongsPackage:com.wzy.demo.service.api.common
 *@author:wzy
 *@createTime:2022-08-08 20:15
 *@description:TODO
 *@version:1.0
 */
@ApiModel(value = "返回结果集")
@SuppressWarnings("unchecked")
public class Result<T> implements Serializable {

    private Integer code = 200;

    private String message="操作成功";

    private T data;

    private Integer total;

    private boolean success;

    public static <T> Result<T> success() {return success(null);}

    public static <T> Result<T> success(T data){return success(data,null);}

    public static <T> Result<T> success(T data,Integer totalCount) {
        return new Result(ResultCodeEnum.SUCCESS,data,totalCount,true);
    }

    public static <T> Result<T> error(ResultCodeEnum resultCodeEnum) {
        return new Result(resultCodeEnum,null,null,false);
    }
    public static <T> Result<T> error(String errMsg) {
        Result result = new Result(ResultCodeEnum.ERROR, null, null, false);
        result.setMessage(errMsg);
        return result;
    }

    public Result<T> setError(Integer code,String msg) {
        this.code=code;
        this.message=msg;
        this.success=false;
        return this;
    }

    public static <T> Result<T> build() {
        Result<T> result = new Result<T>();
        result.setSuccess(true);
        return result;
    }

    public static <T> Result<T> build(T data,Integer totalCount) {
        Result<T> result = new Result<>();
        result.setData(data);
        result.setTotal(totalCount);
        result.setSuccess(true);
        return result;
    }

    public static <T> Result<T> build(T data) {
        Result<T> result = new Result<>();
        result.setData(data);
        result.setSuccess(true);
        return result;
    }

    public Result(Integer code, String message, T data, Integer total, boolean success) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.total = total;
        this.success = success;
    }

    public Result(String message, T data, Integer total, boolean success) {
        this.message = message;
        this.data = data;
        this.total = total;
        this.success = success;
    }

    public Result(ResultCodeEnum resultCodeEnum, T data, Integer total, boolean success) {
        this.message = resultCodeEnum.getMsg();
        this.code = resultCodeEnum.getCode();
        this.data = data;
        this.total = total;
        this.success = success;
    }

    public Result() {
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    public Integer getTotal() {
        return total;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
