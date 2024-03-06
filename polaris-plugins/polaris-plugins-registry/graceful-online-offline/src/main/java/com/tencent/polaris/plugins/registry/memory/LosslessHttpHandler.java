package com.tencent.polaris.plugins.registry.memory;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.tencent.polaris.api.plugin.lossless.LosslessManager;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;

public class LosslessHttpHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {


        String contextPath = exchange.getHttpContext().getPath();

        String response = "{\"UP\"}";

        switch (contextPath) {
            case "/online":
                response = LosslessManager.online();
                break;
            case "/offline":
                if ("127.0.0.1".equals(exchange.getRemoteAddress().getAddress().getHostAddress())) {
                    LosslessManager.offline();
                } else {
                    // TODO: 2024/3/5 401
                }
                break;
        }



        exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, response.length());

        writeResponse(response, exchange.getResponseBody());

        exchange.close();
    }

    private void writeResponse(String response, OutputStream os) throws IOException {
        os.write(response.getBytes());
        os.flush();
        os.close();
    }
}
