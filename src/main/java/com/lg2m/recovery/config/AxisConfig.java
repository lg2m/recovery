package com.lg2m.recovery.config;

import com.lg2m.recovery.axis.SpringEngineConfigurationFactory;
import org.apache.axis.EngineConfigurationFactory;
import org.apache.axis.transport.http.AdminServlet;
import org.apache.axis.transport.http.AxisServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AxisConfig {

    @Bean
    public ServletRegistrationBean<?> axisServletRegistrationBean() {
        System.setProperty(EngineConfigurationFactory.SYSTEM_PROPERTY_NAME, SpringEngineConfigurationFactory.class.getName());
        AxisServlet servlet = new AxisServlet();
        return new ServletRegistrationBean<>(servlet, "/services/*");
    }

    //@Bean
    public ServletRegistrationBean<?> axisAdminServletRegistrationBean() {
        ServletRegistrationBean servletBean = new ServletRegistrationBean<>(new AdminServlet(), "/axis-admin");
        servletBean.setLoadOnStartup(100);
        return servletBean;
    }
}
