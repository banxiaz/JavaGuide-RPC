package com.bai.anno;

import com.bai.spring.CustomImportBeanDefinitionRegistrar;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(CustomImportBeanDefinitionRegistrar.class)
public @interface EnableCustomImport {
    //扫描路径
    String[] packages() default {};
}
