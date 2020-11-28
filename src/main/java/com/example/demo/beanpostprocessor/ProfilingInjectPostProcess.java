package com.example.demo.beanpostprocessor;

import com.example.demo.anotation.Profiling;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

@Component
public class ProfilingInjectPostProcess implements BeanPostProcessor {
    private Map<String,Class<?>> map=new HashMap<>();
    @Nullable
   public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Class<?> aClass = bean.getClass();
        if (aClass.isAnnotationPresent(Profiling.class)){
            map.put(beanName,aClass);
        }
        return bean;
    }

    @Nullable
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class<?> aClass = map.get(beanName);
        if (aClass!=null){
            return Proxy.newProxyInstance(bean.getClass().getClassLoader(), aClass.getInterfaces(), (proxy, method, args) -> {
                System.out.println("start");
                long start = System.nanoTime();
                Object invoke = method.invoke(bean, args);
                long finis = System.nanoTime();
                System.out.println("finish = " + (start-finis));
                return invoke;
            });
        }
        return bean;
    }
}
