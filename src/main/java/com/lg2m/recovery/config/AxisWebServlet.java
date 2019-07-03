package com.lg2m.recovery.config;

import org.apache.axis.transport.http.AxisServlet;

import javax.servlet.Servlet;
import javax.servlet.annotation.WebServlet;

@WebServlet(urlPatterns = "/services/*", loadOnStartup = 1)
public class AxisWebServlet extends AxisServlet implements Servlet{

}
