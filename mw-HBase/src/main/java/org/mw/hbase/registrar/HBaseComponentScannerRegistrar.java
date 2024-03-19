package org.mw.hbase.registrar;

import org.mw.hbase.annotation.EnableHBaseClient;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.util.ClassUtils;

/**
 * A registrar for scanning components annotated with {@link org.mw.hbase.annotation.EnableHBaseClient}.
 */
public class HBaseComponentScannerRegistrar implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        // Get the attributes of the @EnableHBase annotation
        String[] basePackages = (String[]) importingClassMetadata.getAnnotationAttributes(EnableHBaseClient.class.getName()).get("basePackages");
        // If no specific packages to scan are specified, use the package of the current class
        if (basePackages.length == 0) {
            basePackages = new String[]{ClassUtils.getPackageName(importingClassMetadata.getClassName())};
        }

        // Custom component scanner
        ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(false);
        // Add a filter to only scan classes annotated with @EnableHBase
        scanner.addIncludeFilter(new AnnotationTypeFilter(EnableHBaseClient.class));

        // Scan the specified packages
        for (String basePackage : basePackages) {
            scanner.findCandidateComponents(basePackage);
        }
    }
}
