package com.kairosds.challenge.pricesapi.infrastructure.adapter.external.oauth2;

import com.kairosds.challenge.pricesapi.application.query.dto.AuthenticationDetail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatusCode;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.web.client.RestClientResponseException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest(classes = {OAuth2Service.class, OAuth2Client.class, JacksonAutoConfiguration.class})
@EnableConfigurationProperties(OAuth2ClientProperties.class)
@AutoConfigureWireMock(port = 0)
class OAuth2ServiceTest {

	private @Value("classpath:/wiremock/oauth2.json") Resource oauth2JsonResource;
	private @Autowired ObjectMapper mapper;
	private @Autowired OAuth2Service service;

	@DynamicPropertySource
	static void properties(DynamicPropertyRegistry registry) {
		registry.add("oauth2.client.auth-server-url", () -> "http://localhost:${wiremock.server.port}");
	}

	@Test
	void authenticationDetail() throws IOException {

		var jsonResponse = mapper.readValue(oauth2JsonResource.getInputStream(), AuthenticationDetail.class);

		stubFor(post(urlEqualTo("/oauth2/token"))
			.willReturn(aResponse()
				.withStatus(200)
				.withHeader("Content-Type", "application/json")
				.withBody(oauth2JsonResource.getContentAsString(StandardCharsets.UTF_8))));

		var result = service.authenticationDetail("code");

		assertThat(result).isEqualTo(jsonResponse);
	}

	@Test
	void authenticationDetailClientError() {

		stubFor(post(urlEqualTo("/oauth2/token"))
			.willReturn(aResponse()
				.withStatus(403)
				.withHeader("Content-Type", "text/plain")
				.withBody("Forbidden")));

		assertThatThrownBy(() -> service.authenticationDetail("code"))
			.isInstanceOf(RestClientResponseException.class)
			.hasMessage("Error occurred")
			.hasFieldOrPropertyWithValue("statusCode", HttpStatusCode.valueOf(403))
			.hasFieldOrPropertyWithValue("statusText", "Forbidden")
			.hasFieldOrPropertyWithValue("responseBody", "Forbidden".getBytes(StandardCharsets.UTF_8));

	}

}