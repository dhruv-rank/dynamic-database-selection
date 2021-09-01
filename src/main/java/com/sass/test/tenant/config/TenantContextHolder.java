package com.sass.test.tenant.config;


public class TenantContextHolder {

    private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();

    public static void setTenantId(String sid) {
        contextHolder.set(sid);
    }

    public static String getTenantId() {
        return contextHolder.get();
    }

    public static void removeTenantId() {
        contextHolder.remove();
    }
}
