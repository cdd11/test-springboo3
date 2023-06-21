package com.tinghai.testspringboo3.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "doc-info")
public class DocInfo {

    private String title = "Demo Title";
    private String description = "Demo Description";
    private String version = "v0.0.1";
    private String websiteName = "Demo Website";
    private String websiteUrl = "http://www.yygnb.com";
}
