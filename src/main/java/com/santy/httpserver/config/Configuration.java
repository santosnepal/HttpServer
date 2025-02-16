package com.santy.httpserver.config;

public class Configuration {
    private int port;
    private String webrootPath;

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getWebrootPath() {
        return webrootPath;
    }

    public void setWebrootPath(String webrootPath) {
        this.webrootPath = webrootPath;
    }
}
