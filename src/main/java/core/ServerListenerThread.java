package core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerListenerThread extends Thread {
    private final static Logger LOGGER = LoggerFactory.getLogger(ServerListenerThread.class);
    private final ServerSocket serverSocket;
    public ServerListenerThread(int port, String webroot)throws IOException{
        this.serverSocket = new ServerSocket(port);
    }
    @Override
    public void run() {
        try {
            while(this.serverSocket.isBound() && !this.serverSocket.isClosed()) {
                Socket socket = this.serverSocket.accept();
                LOGGER.info("Accepted connection from {}", socket.getInetAddress().getHostAddress());
                HttpConnectionWorkerThread httpConnectionWorkerThread = new HttpConnectionWorkerThread(socket);
                httpConnectionWorkerThread.start();
            }
            this.serverSocket.close();
        }catch (IOException e){
            LOGGER.error(e.getMessage());
        }finally {
            if(this.serverSocket !=null) {
                try {
                    this.serverSocket.close();
                } catch (IOException e) {}
               }
        }

    }
}
