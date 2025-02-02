package com.kairosds.challenge.pricesapi.infrastructure.adapter.external.oauth2;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OAuth2ClientTest {

	private @Mock OAuth2ClientProperties properties;
	private @InjectMocks OAuth2Client client;

	@Test
	void testOauth2RestClient() {

		when(properties.clientId()).thenReturn("clientId");
		when(properties.clientSecret()).thenReturn("clientSecret");
		when(properties.authServerUrl()).thenReturn("authServerUrl");

		assertNotNull(client.oauth2RestClient());
	}

}