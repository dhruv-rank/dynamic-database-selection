package com.sass.test.config;

import com.sass.test.model.SaasNumber;

public class SaasContextHolder {

    private static final ThreadLocal<SaasNumber> contextHolder = new ThreadLocal<>();

    public static void setSaasNumber(SaasNumber saasNumber) {
        contextHolder.set(saasNumber);
    }

    public static SaasNumber getSaasNumber() {
        return contextHolder.get();
    }

    public static void removeSaasNumber() {
        contextHolder.remove();
    }
}
