package com.usoft.config;

import org.apache.axis.transport.http.AxisServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfig {
    @Bean
    public ServletRegistrationBean axisServlet() {
        ServletRegistrationBean servlet = new ServletRegistrationBean();
        servlet.setServlet(new AxisServlet());
        servlet.addUrlMappings("/services/*");
        servlet.setLoadOnStartup(1);
        return servlet;
    }
}
