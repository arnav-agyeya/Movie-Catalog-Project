package com.example.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
@ConfigurationProperties("db")
public class DBSettings {

    private Map<String,String> connection;
    private String host;
    private int port;

    public DBSettings() {
    }

    public Map<String, String> getConnection() {
        return connection;
    }

    public void setConnection(Map<String, String> connection) {
        this.connection = connection;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
