package ru.weierstrass.components.cache;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "lino")
public class LinoCacheProperties {

    private String name = "default-lino";
    private String host = "";
    private String user = "";
    private String password = "";
    private int threadCount = 5;
    private long ttl = 0;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHost() {
        return this.host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getUser() {
        return this.user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getThreadCount() {
        return this.threadCount;
    }

    public void setThreadCount(int threadCount) {
        this.threadCount = threadCount;
    }

    public long getTtl() {
        return this.ttl;
    }

    public void setTtl(int ttl) {
        this.ttl = ttl;
    }

}
