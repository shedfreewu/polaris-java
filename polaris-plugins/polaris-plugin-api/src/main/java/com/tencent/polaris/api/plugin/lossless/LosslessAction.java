package com.tencent.polaris.api.plugin.lossless;

public interface LosslessAction {

    void gracefulRegister();

    void gracefulDeregister();

}
