package io.zuppelli.contentservice.service;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Stack;

public abstract class Builder<T> {
    private T obj;

    public Builder(Class<T> tClass) {
        try {
            obj = tClass.newInstance();
        } catch (IllegalAccessException| InstantiationException e) {
            throw new UnsupportedOperationException("Class needs to have a default constructor");
        }
    }

    public final T build() {
        if(null == obj) throw new UnsupportedOperationException();

        try {

            prebuild();

            return obj;

        } finally {
            obj = null;
        }
    }

    protected T getObj() {
        return obj;
    }

    protected abstract void prebuild();

    public final Builder<T> add(String method, Object content) {
        if(null == obj) throw new UnsupportedOperationException();

        try {
            if(! method.startsWith("set")) {
                StringBuilder sb = new StringBuilder("set");
                sb.append(StringUtils.capitalize(method));
                method = sb.toString();
            }
            Stack<Class> interfaces = new Stack<>();

            interfaces.addAll(Arrays.asList(content.getClass().getInterfaces()));

            Method m = getMethod(method, content.getClass(), interfaces);

            m.invoke(obj, content);
        } catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException e) {
            throw new UnsupportedOperationException();
        }

        return this;
    }

    private Method getMethod(String methodName, Class clazz, Stack<Class> interfaces) throws NoSuchMethodException {
        try {
            return obj.getClass().getMethod(methodName, clazz);
        } catch (NoSuchMethodException e) {
            if(interfaces.empty()) throw e;
            return getMethod(methodName, interfaces.pop(), interfaces);
        }
    }
}
