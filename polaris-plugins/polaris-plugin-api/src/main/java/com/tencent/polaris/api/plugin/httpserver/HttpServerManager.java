package com.tencent.polaris.api.plugin.httpserver;

import com.sun.net.httpserver.HttpServer;
import com.tencent.polaris.client.util.NamedThreadFactory;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class HttpServerManager {
    private static volatile Map<Integer, HttpServer> httpServerMap = new ConcurrentHashMap<>();
    private static volatile Map<Integer, ExecutorService> executorServiceMap = new ConcurrentHashMap<>();
    // TODO: 2024/3/4 change
    private static final ThreadFactory threadFactory = new DaemonNamedThreadFactory("prometheus-http");

    public synchronized static HttpServer getHttpServer(Integer port) {
        if (httpServerMap.containsKey(port)) {
            return httpServerMap.get(port);
        }

        try {
            HttpServer httpServer = HttpServer.create(new InetSocketAddress("0.0.0.0", port), 3);
            ExecutorService executor = Executors.newFixedThreadPool(3, threadFactory);
            httpServer.setExecutor(executor);

            httpServerMap.put(port, httpServer);
            executorServiceMap.put(port, executor);
            return httpServer;
        } catch (IOException e) {
            // TODO: 2024/3/4
            throw new RuntimeException(e);
        }
    }

    public static void startServers() {
        for(HttpServer httpServer: httpServerMap.values()) {
            if (Thread.currentThread().isDaemon()) {
                httpServer.start();
                continue;
            }

            Thread httpServerThread = threadFactory.newThread(httpServer::start);
            httpServerThread.start();
            try {
                httpServerThread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private static final class DaemonNamedThreadFactory extends NamedThreadFactory {

        public DaemonNamedThreadFactory(String component) {
            super(component);
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread thread = super.newThread(r);
            thread.setDaemon(true);
            return thread;
        }
    }


}
