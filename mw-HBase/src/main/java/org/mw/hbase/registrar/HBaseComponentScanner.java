package org.mw.hbase.registrar;

import org.mw.hbase.annotation.EnableHBaseClient;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import java.lang.annotation.Annotation;
import java.util.Set;

/**
 * @author devon.ye@foxmail.com
 * @datetime 2024/3/20 01:04
 * @description
 */


public class HBaseComponentScanner extends ClassPathScanningCandidateComponentProvider {
    public HBaseComponentScanner(BeanDefinitionRegistry registry) {
        super(false);
        super.addIncludeFilter(new AnnotationTypeFilter(EnableHBaseClient.class));
    }

    @Override
    protected boolean isCandidateComponent(org.springframework.core.type.classreading.MetadataReader metadataReader) {
        return true;
    }

    public static Set<BeanDefinition> scan(String basePackage, Class<? extends Annotation> annotationClass) {
        ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(false);
        scanner.addIncludeFilter(new AnnotationTypeFilter(annotationClass));
        return scanner.findCandidateComponents(basePackage);
    }
}
