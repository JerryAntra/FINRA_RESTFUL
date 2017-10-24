package com.FINRA.pojo;

import org.springframework.http.HttpStatus;

public class RestResponseStatus {

    private HttpStatus status;

    public RestResponseStatus(HttpStatus statusCode) {
        this.status = statusCode;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
