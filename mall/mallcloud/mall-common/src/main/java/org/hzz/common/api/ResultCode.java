package org.hzz.common.api;

public enum ResultCode implements ICode{
    SUCCESS(200,"操作成功"),
    FAILED(500,"操作失败");


    private int code;
    private String message;

    private ResultCode(int code ,String message){
        this.code = code;
        this.message = message;
    }
    @Override
    public int getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
