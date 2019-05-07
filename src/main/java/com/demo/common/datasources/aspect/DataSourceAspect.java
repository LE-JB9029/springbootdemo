package com.demo.common.datasources.aspect;

import com.demo.common.datasources.DataSourceNames;
import com.demo.common.datasources.DynamicDataSource;
import com.demo.common.datasources.annotation.DataSource;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 多数据源，切面处理类
 */
@Aspect
@Component
public class DataSourceAspect implements Ordered {

    /*
     * 按注解标签无法找到dao层切入点，所以加入dao包扫描
     */
    @Pointcut("execution(* com.demo.*.dao.*.*(..)) " +
            "|| @annotation(com.demo.common.datasources.annotation.DataSource) " +
            "|| @within(com.demo.common.datasources.annotation.DataSource)")
    public void dataSourcePointCut() {

    }

    @Around("dataSourcePointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        MethodSignature sign = (MethodSignature) point.getSignature();
        Method method = sign.getMethod();
        //获取方法上的注解
        DataSource ds = method.getAnnotation(DataSource.class);
        if (ds == null) {
            //获取类上的注解
            ds = point.getTarget().getClass().getAnnotation(DataSource.class);
            if (ds == null) {
                //获取接口上的注解
                for (Class<?> cls : point.getTarget().getClass().getInterfaces()) {
                    ds = cls.getAnnotation(DataSource.class);
                    if (ds != null) {
                        break;
                    }
                }
            }
        }
        if (ds == null) {
            DynamicDataSource.setDataSource(DataSourceNames.FIRST);
        } else {
            DynamicDataSource.setDataSource(ds.name());
        }

        try {
            return point.proceed();
        } finally {
            DynamicDataSource.clearDataSource();
        }
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
