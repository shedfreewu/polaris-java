package com.tencent.polaris.api.plugin.lossless;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LosslessManager {

    private static Map<String, String> status = new ConcurrentHashMap<>();

    private static Map<String, LosslessAction> losslessActionMap = new ConcurrentHashMap<>();


    public static Map<String, String> getStatus() {
        return status;
    }

    public static Map<String, LosslessAction> getLosslessActionMap() {
        return losslessActionMap;
    }

    public static String online() {
        return "{\"UP\"}";
    }

    public static void offline() {
        losslessActionMap.forEach((key, losslessAction) -> {
            losslessAction.gracefulDeregister();
        });
    }



}
