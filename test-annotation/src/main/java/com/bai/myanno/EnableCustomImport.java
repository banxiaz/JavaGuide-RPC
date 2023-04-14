package com.bai.myanno;

import com.bai.spring.CustomImportBeanDefinitionRegistrar;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 启用自动导入
 *
 * @author huan.fu 2021/4/14 - 上午9:29
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(CustomImportBeanDefinitionRegistrar.class)
public @interface EnableCustomImport {
    //扫描哪些包
    String[] packages() default {};
}
