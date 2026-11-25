package com.mylecture.exercise8;

import com.mylecture.exercise7.Display;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class Injector {
    public static void injectDependencies(Object obj) {
        Class<?> clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field f : fields) {
            if (f.isAnnotationPresent(Inject.class)) {
                f.setAccessible(true);
                Class<?> fieldType = f.getType();
                try {
                    Constructor<?> constructor = f.getType().getConstructor();
                    f.set(obj, constructor.newInstance());
                } catch (NoSuchMethodException e) {
                    throw new RuntimeException(e);
                } catch (InvocationTargetException e) {
                    throw new RuntimeException(e);
                } catch (InstantiationException e) {
                    throw new RuntimeException(e);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }

            }
        }
    }

    public static void main(String[] args) {
        NotificationManager manager = new NotificationManager();
        Injector.injectDependencies(manager);
        manager.notifyUser();
    }
}
