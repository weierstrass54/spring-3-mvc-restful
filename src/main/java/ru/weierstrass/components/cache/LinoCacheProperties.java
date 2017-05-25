package ru.weierstrass.components.cache;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties( prefix = "linoCache" )
public class LinoCacheProperties {

    private String name = "default-lino";
    private String host = "";
    private String user = "";
    private String password = "";
    private int threadCount = 5;

    public void setName( String name ) {
        this.name = name;
    }

    public void setHost( String host ) {
        this.host = host;
    }

    public void setUser( String user ) {
        this.user = user;
    }

    public void setPassword( String password ) {
        this.password = password;
    }

    public void setThreadCount( int threadCount ) {
        this.threadCount = threadCount;
    }

    public String getName() {
        return this.name;
    }

    public String getHost() {
        return this.host;
    }

    public String getUser() {
        return this.user;
    }

    public String getPassword() {
        return this.password;
    }

    public int getThreadCount() {
        return this.threadCount;
    }

}
