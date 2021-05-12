package ru.admiralnsk.admiralbd.models;

public enum Permission {
    DEPARTURES_READ("read"),
    DEPARTURES_WRITE("write");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
