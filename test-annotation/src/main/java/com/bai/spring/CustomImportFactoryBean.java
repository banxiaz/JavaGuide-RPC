package com.bai.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 工厂Bean，用于构建 CustomImport 注解标注的类，如何进行实例化
 *
 * @author huan.fu 2021/4/14 - 上午9:44
 */
public class CustomImportFactoryBean implements FactoryBean<Object>, ApplicationContextAware {
    private Class<?> type;
    private String beanName;
    private ApplicationContext applicationContext;

    /**
     * 此处构建的对象，如果需要依赖Spring Bean的话，需要自己构建进去，默认不会自动注入，即默认情况下@Autowired注解不生效
     */
    @Override
    public Object getObject() throws Exception {
        Object instance = this.type.getDeclaredConstructor().newInstance();
        applicationContext.getAutowireCapableBeanFactory().autowireBean(instance);
        return instance;
    }

    @Override
    public Class<?> getObjectType() {
        return type;
    }

    public Class<?> getType() {
        return type;
    }

    public void setType(Class<?> type) {
        this.type = type;
    }

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
