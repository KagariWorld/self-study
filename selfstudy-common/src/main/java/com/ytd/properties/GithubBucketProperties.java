package com.ytd.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "github.bucket")
@Data
public class GithubBucketProperties {

    private String user;
    private String repository;
    private String accessToken;
    private String url;
    private String api;

}
