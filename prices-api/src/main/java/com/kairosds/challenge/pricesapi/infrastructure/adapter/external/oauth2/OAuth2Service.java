package com.kairosds.challenge.pricesapi.infrastructure.adapter.external.oauth2;

import com.kairosds.challenge.pricesapi.application.query.dto.AuthenticationDetail;
import com.kairosds.challenge.pricesapi.application.query.handle.AuthenticationQueryHandler;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientResponseException;

import java.nio.charset.StandardCharsets;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@EnableConfigurationProperties(OAuth2ClientProperties.class)
public class OAuth2Service implements AuthenticationQueryHandler {

	private final OAuth2ClientProperties properties;
	private final RestClient oauth2RestClient;

	@Override
	public AuthenticationDetail authenticationDetail(String code) {
		var map = new LinkedMultiValueMap<String, String>();
		map.add("redirect_uri", properties.redirectUri());
		map.add("grant_type", properties.grantType());
		map.add("code", code);
		return oauth2RestClient.post()
			.uri("/oauth2/token")
			.body(map)
			.retrieve()
			.onStatus(HttpStatusCode::is4xxClientError, (request, response) -> {
				throw new RestClientResponseException("Error occurred",
					response.getStatusCode(),
					response.getStatusText(),
					response.getHeaders(),
					response.getBody().readAllBytes(),
					StandardCharsets.UTF_8);
			})
			.body(AuthenticationDetail.class);
	}
}
