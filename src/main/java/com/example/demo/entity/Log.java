package com.example.demo.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "logs")
public class Log {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pid;

    @ManyToOne(targetEntity = Logger.class)
    @JoinColumn(name = "logger_pid")
    private Logger logger;

    @Column(name = "message", columnDefinition = "TEXT")
    private String message;

    @Column(name = "url", length = 1000)
    private String url;

    @Column(name = "ip", length = 15)
    private String ip;

    @Column(name = "stack_trace", columnDefinition = "TEXT")
    private String stack;

    @OneToOne(targetEntity = Params.class, mappedBy = "log", cascade = CascadeType.ALL)
    private Params params;

    @Column(name = "add_info", columnDefinition = "TEXT")
    private String addInfo;

    public Log() {
    }

    public Long getPid() {
        return pid;
    }

    public Logger getLogger() {
        return logger;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
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

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getStack() {
        return stack;
    }

    public void setStack(String stack) {
        this.stack = stack;
    }

    public Params getParams() {
        return params;
    }

    public void setParams(Params params) {
        this.params = params;
    }

    public String getAddInfo() {
        return addInfo;
    }

    public void setAddInfo(String addInfo) {
        this.addInfo = addInfo;
    }
}
