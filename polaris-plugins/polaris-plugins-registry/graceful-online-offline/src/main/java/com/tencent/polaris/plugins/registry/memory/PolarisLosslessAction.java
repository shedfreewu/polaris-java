package com.tencent.polaris.plugins.registry.memory;

import com.tencent.polaris.api.core.ProviderAPI;
import com.tencent.polaris.api.plugin.lossless.LosslessAction;
import com.tencent.polaris.api.plugin.lossless.LosslessManager;
import com.tencent.polaris.api.rpc.InstanceRegisterRequest;

public class PolarisLosslessAction implements LosslessAction {

    ProviderAPI providerAPI;

    InstanceRegisterRequest instanceRegisterRequest;

    public PolarisLosslessAction(ProviderAPI providerAPI,
        InstanceRegisterRequest instanceRegisterRequest) {
        this.providerAPI = providerAPI;
        this.instanceRegisterRequest = instanceRegisterRequest;
    }

    @Override
    public void gracefulRegister() {
        new Thread(() -> {
            LosslessManager.getStatus().put("polaris", "DOWN");

            // 如果 curl 能联通
            try {
                // default sleep 120s
                Thread.sleep(120 * 1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            providerAPI.registerInstance(instanceRegisterRequest);

            LosslessManager.getStatus().put("polaris", "UP");

        }).start();
    }

    @Override
    public void gracefulDeregister() {

    }
}
