package com.chong.xinyuproject_new.utils;

/**
 * 公共的响应对象
 */
public class CommonResponse<T> {
    private boolean success=true; //是否成功
    private String message; //返回的消息
    private T content; //返回的内容

    public CommonResponse(){

    }

    public CommonResponse(String message, T content) {
        this.message = message;
        this.content = content;
    }

    public CommonResponse(boolean success, String message, T content) {
        this.success = success;
        this.message = message;
        this.content = content;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "CommonResponse{" +
                "success=" + success +
                ", message='" + message + '\'' +
                ", content=" + content +
                '}';
    }
}
