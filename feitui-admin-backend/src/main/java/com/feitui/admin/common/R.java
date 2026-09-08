package com.feitui.admin.common;

import lombok.Data;

@Data
public class R<T> {
    private Integer code;
    private String message;
    private T data;

    public static <T> R<T> ok() {
        return build(200, "success", null);
    }

    public static <T> R<T> ok(T data) {
        return build(200, "success", data);
    }

    public static <T> R<T> ok(String message, T data) {
        return build(200, message, data);
    }

    public static <T> R<T> fail(String message) {
        return build(500, message, null);
    }

    public static <T> R<T> fail(Integer code, String message) {
        return build(code, message, null);
    }

    private static <T> R<T> build(Integer code, String message, T data) {
        R<T> r = new R<>();
        r.setCode(code);
        r.setMessage(message);
        r.setData(data);
        return r;
    }
}