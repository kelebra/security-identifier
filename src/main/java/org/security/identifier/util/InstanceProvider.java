package org.security.identifier.util;

public interface InstanceProvider<T> {

    T provide(String body, char checkDigit);
}
