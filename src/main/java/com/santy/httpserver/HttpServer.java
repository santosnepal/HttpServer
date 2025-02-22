package com.santy.httpserver;

import com.santy.httpserver.config.Configuration;
import com.santy.httpserver.config.ConfigurationManager;
import com.santy.httpserver.config.HttpConfigurationException;
import core.ServerListenerThread;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 *
 * Driver class for HTTP Server
 *
 */
public class HttpServer {
    private final static Logger LOGGER = LoggerFactory.getLogger(HttpServer.class);
    public static void main(String[] args) {
        LOGGER.info("Starting HTTP Server....");
        try {
            ConfigurationManager.getInstance().loadConfigurationFile("src/main/resources/http.json");
            Configuration conf = ConfigurationManager.getInstance().getCurrentConfiguration();
            LOGGER.info("Server Started at port: {}", conf.getPort());
            LOGGER.info("Using web root: {}", conf.getWebrootPath());
            ServerListenerThread serverListener = new ServerListenerThread(conf.getPort(),conf.getWebrootPath());
            serverListener.start();
        } catch (IOException e) {
            throw new HttpConfigurationException(e);
        }
    }
}
