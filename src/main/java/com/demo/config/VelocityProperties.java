package com.demo.config;


import org.springframework.boot.autoconfigure.template.AbstractTemplateViewResolverProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.servlet.view.velocity.VelocityViewResolver;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@ConfigurationProperties(
        prefix = "velocity"
)
public class VelocityProperties extends AbstractTemplateViewResolverProperties {

    public static final String DEFAULT_RESOURCE_LOADER_PATH = "classpath:/templates/";
    public static final String DEFAULT_PREFIX = "";
    public static final String DEFAULT_SUFFIX = ".vm";
    private String dateToolAttribute;
    private String numberToolAttribute;
    private Map<String, String> attributes = new HashMap();
    private String resourceLoaderPath = DEFAULT_RESOURCE_LOADER_PATH;
    private String toolboxConfigLocation;
    private boolean preferFileSystemAccess = true;

    public VelocityProperties() {
        super(DEFAULT_PREFIX, DEFAULT_SUFFIX);
    }

    public String getDateToolAttribute() {
        return this.dateToolAttribute;
    }

    public void setDateToolAttribute(String dateToolAttribute) {
        this.dateToolAttribute = dateToolAttribute;
    }

    public String getNumberToolAttribute() {
        return this.numberToolAttribute;
    }

    public void setNumberToolAttribute(String numberToolAttribute) {
        this.numberToolAttribute = numberToolAttribute;
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, String> attributes) {
        this.attributes = attributes;
    }

    public String getResourceLoaderPath() {
        return this.resourceLoaderPath;
    }

    public void setResourceLoaderPath(String resourceLoaderPath) {
        this.resourceLoaderPath = resourceLoaderPath;
    }

    public String getToolboxConfigLocation() {
        return this.toolboxConfigLocation;
    }

    public void setToolboxConfigLocation(String toolboxConfigLocation) {
        this.toolboxConfigLocation = toolboxConfigLocation;
    }

    public boolean isPreferFileSystemAccess() {
        return this.preferFileSystemAccess;
    }

    public void setPreferFileSystemAccess(boolean preferFileSystemAccess) {
        this.preferFileSystemAccess = preferFileSystemAccess;
    }

    public void applyToViewResolver(Object viewResolver) {
        super.applyToViewResolver(viewResolver);
        VelocityViewResolver resolver = (VelocityViewResolver) viewResolver;
        resolver.setToolboxConfigLocation(this.getToolboxConfigLocation());
        resolver.setDateToolAttribute(this.getDateToolAttribute());
        resolver.setNumberToolAttribute(this.getNumberToolAttribute());
        resolver.setRequestContextAttribute("rc");
        Properties properties = new Properties();
        properties.putAll(this.attributes);
        resolver.setAttributes(properties);
    }
}