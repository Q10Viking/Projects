package org.hzz.common.api;

public class CommonResult<T> {
    private int code;
    private String message;
    private T data;

    public CommonResult(int code,String message,T data){
        this.code = code;
        this.message = message;
        this.data = data;
    }


    public static <T> CommonResult<T> success(T data){
        return new CommonResult<T>(
                ResultCode.SUCCESS.getCode(),
                ResultCode.SUCCESS.getMessage(),
                data);
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
