package com.kairosds.challenge.pricesapi.infrastructure.adapter.external.oauth2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
public class OAuth2Client {

	private final OAuth2ClientProperties properties;

	@Bean
	RestClient oauth2RestClient() {
		var defaultHeaders = new HttpHeaders();
		defaultHeaders.setContentType(MediaType.MULTIPART_FORM_DATA);
		defaultHeaders.setBasicAuth(properties.clientId(), properties.clientSecret());
		return RestClient.create(properties.authServerUrl()).mutate().defaultHeaders(httpHeaders -> httpHeaders.putAll(defaultHeaders))
			.build();
	}

}
