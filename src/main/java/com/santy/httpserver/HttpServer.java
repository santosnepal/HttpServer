package com.santy.httpserver;

import com.santy.httpserver.config.Configuration;
import com.santy.httpserver.config.ConfigurationManager;
import com.santy.httpserver.config.HttpConfigurationException;

import java.io.IOException;

/**
 *
 * Driver class for HTTP Server
 *
 */
public class HttpServer {
    public static void main(String[] args) {
        System.out.println("Starting HTTP Server....");
        try {
            ConfigurationManager.getInstance().loadConfigurationFile("src/main/resources/http.json");
            Configuration conf = ConfigurationManager.getInstance().getCurrentConfiguration();
            System.out.println("Server Started at port: " + conf.getPort());
            System.out.println("Using web root: "+conf.getWebrootPath());
        } catch (IOException e) {
            throw new HttpConfigurationException(e);
        }
    }
}
