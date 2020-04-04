package com.logistics.cloud.eunm;


public enum  OperationType {

    /**
     * 方法的操作类型
     */
    UNKNOWN("unknown"),
    SELECT("select"),
    UPDATE("update"),
    DELETE("delete"),
    INSERT("insert");

    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    OperationType(String type){
        this.type = type;
    }


}
