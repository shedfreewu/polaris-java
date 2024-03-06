package com.tencent.polaris.plugins.registry.memory;

import com.sun.net.httpserver.HttpServer;
import com.tencent.polaris.api.plugin.httpserver.HttpServerManager;

public class GracefulHttpServer {

    private static final String DEFAULT_PATH = "/metrics";

    private int port;

    private String path;

    public GracefulHttpServer(int port) {
        this.port = port;

        HttpServer httpServer = HttpServerManager.getHttpServer(this.port);
        httpServer.createContext("/online", new LosslessHttpHandler());

    }

    public void stopServer() {

    }

}
