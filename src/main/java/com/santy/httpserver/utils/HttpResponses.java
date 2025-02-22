package com.santy.httpserver.utils;

public class HttpResponses {
    private String getSuccessResponse() {
        return "HTTP/1.1 200 OK";
    }

    private String getFailedResponse() {
        return "HTTP/1.1 500 Internal Server Error";
    }
    public String getResponse(String response) {
        String CRLF = "\r\n";
        return this.getSuccessResponse()
                +CRLF
                +"Content-Length: "
                +response.getBytes().length
                +CRLF
                +CRLF
                +response
                +CRLF
                +CRLF;
    }
}
