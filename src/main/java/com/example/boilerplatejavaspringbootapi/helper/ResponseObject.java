package com.example.boilerplatejavaspringbootapi.helper;

import java.util.Optional;

/**
 *
 * @author ardyardyaaan
 */

public class ResponseObject {

    private String status;
    private Optional<?> data;
    private String message;

    public ResponseObject(String status, Optional<?> data, String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public ResponseObject() {
    }

    public ResponseObject failedResponse() {
        ResponseObject response = new ResponseObject("failed", null, "Internal Server Error");
        return response;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Optional<?> getData() {
        return data;
    }

    public void setData(Optional<?> data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
