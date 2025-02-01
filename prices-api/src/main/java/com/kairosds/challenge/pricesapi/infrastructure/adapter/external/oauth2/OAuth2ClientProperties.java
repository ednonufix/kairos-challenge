package com.kairosds.challenge.pricesapi.infrastructure.adapter.external.oauth2;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "oauth2.client")
public record OAuth2ClientProperties(String clientId,
                                     String clientSecret,
                                     String grantType,
                                     String authServerUrl,
                                     String redirectUri) {
}
