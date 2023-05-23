package com.nhnacademy.spring.jpa_security.exception;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

public class AlreadyRegisteredException extends RuntimeException {
    public AlreadyRegisteredException(Class<?> className, Object id) {
        super(getSlreadyRegisteredExceptionMessage(className, id));
    }

    private static String getSlreadyRegisteredExceptionMessage(Class<?> clazz, Object id) {
        StringBuilder stringBuilder = new StringBuilder();

        return stringBuilder.append("Already Registered ")
                .append(clazz.getSimpleName())
                .append(" ( ")
                .append(Objects.equals(id.getClass(), Long.class) ? id :
                        Arrays.stream(id.getClass().getDeclaredFields())
                                .map(x -> {
                                    x.setAccessible(true);
                                    try {
                                        return x.getName() + " : " + x.get(id);
                                    } catch (IllegalAccessException | IllegalArgumentException e1) {
                                        return x.getName() + " : null";
                                    }
                                })
                                .collect(Collectors.joining(", ")))
                .append(" )").toString();
    }
}
