package com.bai.spring;

import com.bai.myanno.CustomImport;
import com.bai.myanno.EnableCustomImport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class CustomImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar, ResourceLoaderAware, EnvironmentAware {
    private static final Logger log = LoggerFactory.getLogger(CustomImportBeanDefinitionRegistrar.class);

    private Environment environment;
    private ResourceLoader resourceLoader;

    @Override
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry registry) {
        if (!annotationMetadata.hasAnnotation(EnableCustomImport.class.getName())) {
            return;
        }
        Map<String, Object> annotationAttributesMap = annotationMetadata.getAnnotationAttributes(EnableCustomImport.class.getName());
        System.out.println(annotationAttributesMap); //{packages=[com.bai]}
        AnnotationAttributes annotationAttributes = Optional.ofNullable(AnnotationAttributes.fromMap(annotationAttributesMap)).orElseGet(AnnotationAttributes::new);
        // 获取需要扫描的包
        String[] packages = retrievePackagesName(annotationMetadata, annotationAttributes);
        System.out.println(Arrays.toString(packages)); //[com.bai]
        // useDefaultFilters = false,即第二个参数 表示不扫描 @Component、@ManagedBean、@Named 注解标注的类
        ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(registry, false, environment, resourceLoader);
        // 添加我们自定义注解的扫描
        scanner.addIncludeFilter(new AnnotationTypeFilter(CustomImport.class));
        // 扫描包
        for (String needScanPackage : packages) {
            Set<BeanDefinition> candidateComponents = scanner.findCandidateComponents(needScanPackage);
            System.out.println(candidateComponents);
            try {
                registerCandidateComponents(registry, candidateComponents);
            } catch (ClassNotFoundException e) {
                log.error(e.getMessage(), e);
            }
        }
    }
    // line51
    //[Generic bean: class [com.bai.HelloService];
    // scope=;
    // abstract=false;
    // lazyInit=null;
    // autowireMode=0;
    // dependencyCheck=0;
    // autowireCandidate=true;
    // primary=false;
    // factoryBeanName=null;
    // factoryMethodName=null;
    // initMethodName=null;
    // destroyMethodName=null;
    // defined in file [D:\Java_Files\guide-rpc-framework-master\test-annotation\target\classes\com\bai\HelloService.class],
    // Generic bean: class [com.bai.HelloService2];
    // scope=;
    // abstract=false;
    // lazyInit=null;
    // autowireMode=0;
    // dependencyCheck=0;
    // autowireCandidate=true;
    // primary=false;
    // factoryBeanName=null;
    // factoryMethodName=null;
    // initMethodName=null;
    // destroyMethodName=null;
    // defined in file [D:\Java_Files\guide-rpc-framework-master\test-annotation\target\classes\com\bai\HelloService2.class]]
    /**
     * 获取需要扫描的包
     */
    private String[] retrievePackagesName(AnnotationMetadata annotationMetadata, AnnotationAttributes annotationAttributes) {
        String[] packages = annotationAttributes.getStringArray("packages");
        if (packages.length > 0) {
            return packages;
        }
        String className = annotationMetadata.getClassName();
        int lastDot = className.lastIndexOf('.');
        return new String[]{className.substring(0, lastDot)};
    }

    /**
     * 注册 BeanDefinition
     */
    private void registerCandidateComponents(BeanDefinitionRegistry registry, Set<BeanDefinition> candidateComponents) throws ClassNotFoundException {
        for (BeanDefinition candidateComponent : candidateComponents) {
            if (candidateComponent instanceof AnnotatedBeanDefinition) {
                AnnotatedBeanDefinition annotatedBeanDefinition = (AnnotatedBeanDefinition) candidateComponent;
                AnnotationMetadata annotationMetadata = annotatedBeanDefinition.getMetadata();
                Map<String, Object> customImportAnnotationAttributesMap = annotationMetadata.getAnnotationAttributes(CustomImport.class.getName());
                AnnotationAttributes customImportAnnotationAttributes = Optional.ofNullable(AnnotationAttributes.fromMap(customImportAnnotationAttributesMap)).orElseGet(AnnotationAttributes::new);
                String beanName = customImportAnnotationAttributes.getString("beanName");
                String className = annotationMetadata.getClassName();
                Class<?> clazzName = Class.forName(className);
                AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition(CustomImportFactoryBean.class)
                        .addPropertyValue("type", clazzName)
                        .addPropertyValue("beanName", beanName)
                        .setAutowireMode(AbstractBeanDefinition.AUTOWIRE_BY_TYPE)
                        .setRole(BeanDefinition.ROLE_INFRASTRUCTURE)
                        .getBeanDefinition();
                registry.registerBeanDefinition(beanName, beanDefinition);

            }
        }
    }


    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }
}
