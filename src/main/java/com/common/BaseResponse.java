package com.common;

public class BaseResponse<T> {
    private Boolean success;
    private Integer code;
    private String message;
    private T result;

    private BaseResponse(Boolean success, Integer code, String message, T result) {
        this.success = success;
        this.code = code;
        this.message = message;
        this.result = result;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public static <T> BaseResponse success(T result) {
        return new BaseResponse(true, 200, "OK", result);
    }

    public static <T> BaseResponse failure(T result) {
        return new BaseResponse(false, 404, "FUCK", result);
    }
}
