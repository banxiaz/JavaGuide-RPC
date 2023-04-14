package com.bai.spring;

import com.bai.anno.CustomAnnotation;
import com.bai.anno.EnableCustomImport;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.StandardAnnotationMetadata;
import org.springframework.core.type.filter.AnnotationTypeFilter;

//实现此接口(FactoryBean)，可以让我们自定义实例化出我们要构建的Bean.
public class CustomImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar, ResourceLoaderAware {
    private ResourceLoader resourceLoader;

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        //获取自定义扫描注解的信息
        AnnotationAttributes annotationAttributes = AnnotationAttributes.fromMap(importingClassMetadata.getAnnotationAttributes(EnableCustomImport.class.getName()));
        System.out.println(annotationAttributes);

        String[] scanPackages = new String[0];
        if (annotationAttributes != null) {
            scanPackages = annotationAttributes.getStringArray("packages"); //这里用"packages"取出属性
        }
        //否则扫描当前注解目录下的所有packages
        if (scanPackages.length == 0) {
            scanPackages = new String[]{((StandardAnnotationMetadata) importingClassMetadata).getIntrospectedClass().getPackage().getName()};
        }

        //增加自定义注解的扫描，同时保留spring预设的bean注解的扫描（@Component等）
        ClassPathBeanDefinitionScanner serviceScanner = new ClassPathBeanDefinitionScanner(registry, true);
        // 添加我们自定义注解的扫描
        serviceScanner.addIncludeFilter(new AnnotationTypeFilter(CustomAnnotation.class));
        if (resourceLoader != null) {
            serviceScanner.setResourceLoader(resourceLoader);
        }

        int beanCount = serviceScanner.scan(scanPackages);
        System.out.println("bean count: " + beanCount);
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }
}
