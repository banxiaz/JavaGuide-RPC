package com.bai.spring;

import com.bai.anno.CustomAnnotation;
import com.bai.anno.Excel;
import lombok.SneakyThrows;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

@Component
public class SpringBeanPostProcessor implements BeanPostProcessor {
    public SpringBeanPostProcessor() {
    }

    // beanName这是bean的名字
    // bean就是那个bean
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("[Before] " + beanName + " " + bean.getClass().getName());
        if (bean.getClass().isAnnotationPresent(CustomAnnotation.class)) {
            CustomAnnotation customAnnotation = bean.getClass().getAnnotation(CustomAnnotation.class);
            System.out.println("customAnnotation.baseName() " + customAnnotation.baseName());
        }
        return bean;
    }

    @SneakyThrows
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("[After] " + beanName + " " + bean.getClass().getName());
        if (bean.getClass().isAnnotationPresent(CustomAnnotation.class)) {
            Field[] fields = bean.getClass().getDeclaredFields();
            for (Field f : fields) {
                if (f.isAnnotationPresent(Excel.class)) {
                    System.out.println(f.getAnnotation(Excel.class).name() + "yes");

                    //玩一下！
                    f.setAccessible(true);
                    f.set(bean, f.getAnnotation(Excel.class).name() + "yes");
                }
            }
            //玩一下！
            Method method = bean.getClass().getMethod("sayHello");
            method.invoke(bean);
        }
        System.out.println();
        return bean;
    }
}
