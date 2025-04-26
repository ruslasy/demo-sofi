package com.example.demo;

import jakarta.json.bind.annotation.JsonbProperty;

public class LogWsJson {
    @JsonbProperty("loggerName")
    private String loggerName;

    @JsonbProperty("level")
    private String level;

    @JsonbProperty("message")
    private String message;

    @JsonbProperty(value = "url", nillable = true)
    private String url;

    @JsonbProperty("params")
    private String params;

    @JsonbProperty("addInfo")
    private String addInfo;

    @JsonbProperty(value = "stackTrace", nillable = true)
    private String stackTrace;


    public String getLoggerName() {
        return loggerName;
    }

    public void setLoggerName(String loggerName) {
        this.loggerName = loggerName;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getAddInfo() {
        return addInfo;
    }

    public void setAddInfo(String addInfo) {
        this.addInfo = addInfo;
    }

    public String getStackTrace() {
        return stackTrace;
    }

    public void setStackTrace(String stackTrace) {
        this.stackTrace = stackTrace;
    }
}
