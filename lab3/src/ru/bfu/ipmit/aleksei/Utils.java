package ru.bfu.ipmit.aleksei;

public class Utils {
    public static <T> T getValueOrDefault(T value, T defaultValue) {
        return value == null ? defaultValue : value;
    }
}
