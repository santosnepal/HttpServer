package com.santy.httpserver;

import com.santy.httpserver.config.Configuration;
import com.santy.httpserver.config.ConfigurationManager;
import com.santy.httpserver.config.HttpConfigurationException;
import com.santy.httpserver.utils.HtmlReader;
import com.santy.httpserver.utils.HttpResponses;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

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
            HtmlReader html = new HtmlReader();
            HttpResponses responses = new HttpResponses();
            System.out.println("Server Started at port: " + conf.getPort());
            System.out.println("Using web root: "+conf.getWebrootPath());
            try(ServerSocket serverSocket = new ServerSocket(conf.getPort())) {
               Socket socket  =  serverSocket.accept();
                InputStream input = socket.getInputStream();
                OutputStream output = socket.getOutputStream();
                String returnString = responses.getResponse(html.getHtmlFile("page.html"));
                output.write(returnString.getBytes());
                output.flush();
                /*
                 * TODO
                 *  Read from socket and write from socket
                 */


                input.close();
                output.close();
                socket.close();
                serverSocket.close();
            }
        } catch (IOException e) {
            throw new HttpConfigurationException(e);
        }
    }
}
