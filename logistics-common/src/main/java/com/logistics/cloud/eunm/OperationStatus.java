package com.logistics.cloud.eunm;

public enum OperationStatus {

    USER("user"),
    ADMIN("admin");

    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    OperationStatus(String status){
        this.status = status;
    }
}
