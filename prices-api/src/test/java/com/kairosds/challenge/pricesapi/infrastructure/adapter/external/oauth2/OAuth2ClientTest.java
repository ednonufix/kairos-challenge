package com.kairosds.challenge.pricesapi.infrastructure.adapter.external.oauth2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = OAuth2Client.class)
@EnableConfigurationProperties(OAuth2ClientProperties.class)
class OAuth2ClientTest {

	private @Autowired OAuth2Client client;

	@Test
	void oauth2RestClient() {
		assertThat(client.oauth2RestClient()).isNotNull();
	}

}