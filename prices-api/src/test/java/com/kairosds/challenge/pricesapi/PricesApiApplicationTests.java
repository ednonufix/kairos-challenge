package com.kairosds.challenge.pricesapi;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class PricesApiApplicationTests {

	private @MockitoBean ClientRegistrationRepository clientRegistrationRepository;
	private @MockitoBean JwtDecoder jwtDecoder;

	@Test
	void contextLoads() {
		assertThat(PricesApiApplication.class).isNotNull();
	}

}
