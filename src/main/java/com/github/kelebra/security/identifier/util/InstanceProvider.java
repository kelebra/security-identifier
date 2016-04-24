package com.github.kelebra.security.identifier.util;

public interface InstanceProvider<T> {

    T provide(String body, char checkDigit);
}
