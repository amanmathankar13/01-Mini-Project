package com.mini_project.props;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "mini-project")
@Configuration
public class AppProperties {

    // This class is used to fetch the data from the application.yml so we didn't write a hard coded data in a classes
    private Map<String, String> messages = new HashMap<>();
    
}
