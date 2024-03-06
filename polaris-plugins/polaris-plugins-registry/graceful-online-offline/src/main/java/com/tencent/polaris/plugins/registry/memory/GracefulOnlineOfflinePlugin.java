package com.tencent.polaris.plugins.registry.memory;

import com.tencent.polaris.api.exception.PolarisException;
import com.tencent.polaris.api.plugin.PluginType;
import com.tencent.polaris.api.plugin.common.InitContext;
import com.tencent.polaris.api.plugin.common.PluginTypes;
import com.tencent.polaris.api.plugin.compose.Extensions;
import com.tencent.polaris.api.plugin.registry.Lossless;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

public class GracefulOnlineOfflinePlugin implements Lossless {

    private final AtomicBoolean firstHandle = new AtomicBoolean(false);

    private Extensions extensions;

    private boolean enable;

    private GracefulHttpServer httpServer;

    @Override
    public String getName() {
        return "graceful";
    }

    @Override
    public PluginType getType() {
        return PluginTypes.LOSSLESS.getBaseType();
    }

    @Override
    public void init(InitContext initContext) throws PolarisException {

    }

    @Override
    public void postContextInit(Extensions extensions) throws PolarisException {
        this.extensions = extensions;
        this.enable = true;
        initHandle();

        System.out.println("postContextInit GracefulOnlineOfflineImpl");
    }

    void initHandle() {
        if (!enable) {
            return;
        }

        if (firstHandle.compareAndSet(false, true)) {
            httpServer = new GracefulHttpServer(28080);
        }
    }

    @Override
    public void destroy() {
        if (Objects.nonNull(httpServer)) {
            httpServer.stopServer();
        }
    }
}
