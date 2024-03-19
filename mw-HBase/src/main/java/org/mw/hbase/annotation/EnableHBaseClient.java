package org.mw.hbase.annotation;

import org.mw.hbase.registrar.HBaseComponentScannerRegistrar;
import org.springframework.context.annotation.Import;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author devon.ye@foxmail.com
 * @datetime 2024/3/20 00:58
 * @description
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(HBaseComponentScannerRegistrar.class)
public @interface EnableHBaseClient {
    String[] basePackages() default {"com.yuntun.hbase"};
}
