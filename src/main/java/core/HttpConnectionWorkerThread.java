package core;

import com.santy.httpserver.utils.HtmlReader;
import com.santy.httpserver.utils.HttpResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class HttpConnectionWorkerThread extends Thread {
    private final Socket socket;
    private final HttpResponses httpResponses;
    private final HtmlReader htmlReader;
    private final static Logger LOGGER = LoggerFactory.getLogger(HttpConnectionWorkerThread.class);
    public HttpConnectionWorkerThread(Socket socket) {
        this.socket = socket;
        this.httpResponses = new HttpResponses();
        this.htmlReader = new HtmlReader();
    }

    @Override
    public void run() {
        InputStream input = null;
        OutputStream output = null;
        try {
            input = this.socket.getInputStream();
            output = this.socket.getOutputStream();
            String returnString = this.httpResponses.getResponse(this.htmlReader.getHtmlFile("page.html"));
            output.write(returnString.getBytes());
            output.flush();
            /*
             * TODO
             *  Read from socket and write from socket
             */
            try {
                sleep(5000);
            } catch (InterruptedException e) {
                LOGGER.error(e.getMessage());
            }
            LOGGER.info("HTTP connection completed.");
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            try {
                if(input != null ) {
                    input.close();
                }
                if(output != null ) {
                    output.close();
                }
                if(this.socket != null ) {
                    this.socket.close();
                }
            } catch (IOException e) {
            }
        }
    }
}
