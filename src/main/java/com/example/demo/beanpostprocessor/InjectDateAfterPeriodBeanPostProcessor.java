package com.example.demo.beanpostprocessor;

import com.example.demo.anotation.DateAfterPeriod;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Calendar;
import java.util.Date;
@Component
public class InjectDateAfterPeriodBeanPostProcessor implements BeanPostProcessor {
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Field[] fields = bean.getClass().getDeclaredFields();
        for (Field field : fields) {
            DateAfterPeriod annotation = field.getAnnotation(DateAfterPeriod.class);
            if (annotation != null) {

                int period = annotation.periodMonth();
                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.MONTH, period);
                field.setAccessible(true);
                Date date =Date.from(cal.toInstant());
                ReflectionUtils.setField(field,bean,date);
            }
        }
        return bean;
    }

    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
