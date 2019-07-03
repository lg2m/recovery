package com.lg2m.recovery.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import javax.jws.WebService;
import java.lang.annotation.Annotation;
import java.util.Set;

@Configuration
@Import(AutoConfiguration.WebServiceScannerRegister.class)
public class AutoConfiguration {

    private static String[] autoScannerPackages() {
        String packageName = AutoConfiguration.class.getPackage().getName();
        int splitPos = packageName.lastIndexOf(".");
        return new String[]{packageName.substring(0, splitPos)};
    }

    @Slf4j
    public static class WebServiceScannerRegister implements ImportBeanDefinitionRegistrar, ResourceLoaderAware {

        private ResourceLoader resourceLoader;

        @Override
        public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
            log.debug("Searching for mappers annotated with @WebService");
            ClassPathWebServiceScanner scanner = new ClassPathWebServiceScanner(registry);

            try {
                if (this.resourceLoader != null) {
                    scanner.setResourceLoader(this.resourceLoader);
                }

                scanner.registerFilter();
                scanner.doScan(autoScannerPackages());
            } catch (IllegalStateException ex) {
                log.error("Could not determine auto-configuration package, automatic webservice scanning disabled.", ex);
            }
        }

        @Override
        public void setResourceLoader(ResourceLoader resourceLoader) {
            this.resourceLoader = resourceLoader;
        }
    }

    public static class ClassPathWebServiceScanner extends ClassPathBeanDefinitionScanner {

        private Class<? extends Annotation> annotationClass = WebService.class;

        public ClassPathWebServiceScanner(BeanDefinitionRegistry registry) {
            super(registry, false);
        }

        public void registerFilter() {
            addIncludeFilter(new AnnotationTypeFilter(this.annotationClass));

            // exclude package-info.java
            addExcludeFilter((metadataReader, metadataReaderFactory) -> {
                String className = metadataReader.getClassMetadata().getClassName();
                return className.endsWith("package-info");
            });
        }

        @Override
        public Set<BeanDefinitionHolder> doScan(String[] basePackages) {
            return super.doScan(basePackages);
        }
    }
}
