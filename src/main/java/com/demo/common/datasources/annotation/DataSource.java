package com.demo.common.datasources.annotation;

import java.lang.annotation.*;

/**
 * 多数据源注解
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface DataSource {
    String name() default "";
}
