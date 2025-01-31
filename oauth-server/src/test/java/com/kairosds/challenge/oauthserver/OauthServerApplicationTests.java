package com.kairosds.challenge.oauthserver;

import org.springframework.boot.test.context.SpringBootTest;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class OauthServerApplicationTests {

	@Test
	void contextLoads() {
		assertThat(OauthServerApplication.class).isNotNull();
	}

}
