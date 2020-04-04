package com.logistics.cloud.eunm;

public enum OperationLogRank {

    /**
     * 日志级别：OFF、FATAL、ERROR、WARN、INFO、DEBUG、TRACE、 ALL。
     */
    OFF("OFF"),
    FATAL("FATAL"),
    ERROR("ERROR"),
    WARN("WARN"),
    INFO("INFO"),
    DEBUG("DEBUG"),
    TRACE("TRACE");


    private String level;


    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    OperationLogRank(String level){
        this.level = level;
    }
}
