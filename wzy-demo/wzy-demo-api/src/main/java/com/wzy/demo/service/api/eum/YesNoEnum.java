package com.wzy.demo.service.api.eum;

public enum YesNoEnum {
    /**
     * 是
     */
    YES(1, "是"),
    /**
     * 否
     */
    NO(0, "否");
    private final Integer code;
    private final String msg;

    YesNoEnum(final Integer code, final String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
