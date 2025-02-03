package com.kairosds.challenge.pricesapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;

@SpringBootTest
class PricesApiApplicationTests {

	private @MockitoBean ClientRegistrationRepository clientRegistrationRepository;
	private @MockitoBean JwtDecoder jwtDecoder;

	@Test
	void contextLoads() {
		assertThat(PricesApiApplication.class).isNotNull();
	}

	@Test
	void mainWithMock() {
		try (MockedStatic<SpringApplication> springApplicationMock = mockStatic(SpringApplication.class)) {
			var mockContext = mock(ConfigurableApplicationContext.class);
			springApplicationMock.when(() -> SpringApplication.run(PricesApiApplication.class, new String[]{}))
				.thenReturn(mockContext);

			PricesApiApplication.main(new String[]{});

			springApplicationMock.verify(() -> SpringApplication.run(PricesApiApplication.class, new String[]{}));
		}
	}

}
