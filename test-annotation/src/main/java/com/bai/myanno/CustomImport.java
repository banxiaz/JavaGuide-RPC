package com.bai.myanno;

import java.lang.annotation.*;

/**
 * 此注解标注的类也会自动加入到 Spring 管理中。
 *
 * @author huan.fu 2021/4/14 - 上午9:23
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CustomImport {
    /**
     * 标识这个bean注入到Spring容器中的名字
     */
    String beanName();
}
