package com.logistics.cloud.eunm;

public enum  ResultCode {
    /* 成功状态码 */
    SUCCESS("200", "成功"),
    FAIL("500", "失败"),

    UNKNOWN("-1", "未知错误"),

    /* 系统500错误*/
    SYSTEM_ERROR("10000", "系统异常，请稍后重试"),


    /* 参数错误：10001-19999 */
    PARAM_IS_INVALID("10001", "参数无效"),


    /* 用户错误：20001-29999*/
    USER_HAS_EXISTED("20001", "用户已存在"),
    USER_LOGIN_FAIL("20002","账号或密码错误"),
    USER_HAS_EXIST("20003","账号已存在"),
    USER_NOT_EXIST("20004","用户不存在，请重新登录"),

    /* 认证失败错误：30001-39999*/
    NO_TOKEN("30001","无token，请重新登录"),
    TOKEN_OUT_TIME("30002","token超时,请重新登录"),
    TOKEN_ILLEGAL("30003","token 认证失败"),

    /* 文件失败错误：40001-49999*/
    EXCEL_NO_SHEET("40001","Excel无Sheet");


    private String code;

    private String message;

    ResultCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String code() {
        return this.code;
    }

    public String message() {
        return this.message;
    }

    public static String getMessage(String name) {
        for (ResultCode item : ResultCode.values()) {
            if (item.name().equals(name)) {
                return item.message;
            }
        }
        return name;
    }

    public static String getCode(String name) {
        for (ResultCode item : ResultCode.values()) {
            if (item.name().equals(name)) {
                return item.code;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return this.name();
    }
}
