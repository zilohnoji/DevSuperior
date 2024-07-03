package com.devsuperior.demo.exceptions;

import java.time.Instant;

public class StandardError {
    private Instant timestamp = Instant.now();
    private Integer status;
    private String error;
    private String path;

    public StandardError() {
    }

    public StandardError(String error, Integer status, String path) {
        this.error = error;
        this.status = status;
        this.path = path;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

}
