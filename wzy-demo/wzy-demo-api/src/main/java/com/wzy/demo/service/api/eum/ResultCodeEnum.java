package com.wzy.demo.service.api.eum;

public enum ResultCodeEnum {
    PARAM_VALIDATE_EXCEPTION(101,"参数异常"),
    SUCCESS(200,"操作成功"),
    ERROR(500,"系统异常");

    private Integer code;

    private String msg;

    ResultCodeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
