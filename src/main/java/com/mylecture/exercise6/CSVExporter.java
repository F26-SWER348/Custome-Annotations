package com.mylecture.exercise6;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class CSVExporter {

    public static <T> String export(List<T> objects) {
        if (objects == null || objects.isEmpty()) {
            return "";
        }

        Class<?> clazz = objects.get(0).getClass();

        List<Field> annotatedFields = Arrays.stream(clazz.getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(CSVColumn.class))
                .sorted(Comparator.comparingInt(field -> field.getAnnotation(CSVColumn.class).order()))
                .collect(Collectors.toList());

        if (annotatedFields.isEmpty()) {
            return "";
        }

        for (Field field : annotatedFields) {
            field.setAccessible(true);
        }

        StringBuilder csv = new StringBuilder();

        String header = annotatedFields.stream()
                .map(field -> field.getAnnotation(CSVColumn.class).name())
                .collect(Collectors.joining(","));
        csv.append(header).append("\n");

        for (T obj : objects) {
            String row = annotatedFields.stream()
                    .map(field -> {
                        try {
                            Object value = field.get(obj);
                            return value != null ? value.toString() : "";
                        } catch (IllegalAccessException e) {
                            throw new RuntimeException("Failed to access field: " + field.getName(), e);
                        }
                    })
                    .collect(Collectors.joining(","));
            csv.append(row).append("\n");
        }

        return csv.toString().trim();
    }
}
