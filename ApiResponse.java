package com.example.crud.dto;

public class ApiResponse<T> {
    private T data;
    private String responseCode;
    private String responseMessage;

    public ApiResponse() {}

    public ApiResponse(T data, String responseCode, String responseMessage) {
        this.data = data;
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
    }

    public T getData() {
        return data;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }
}
