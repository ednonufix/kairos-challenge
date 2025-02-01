package com.kairosds.challenge.pricesapi;

import org.springframework.boot.test.context.SpringBootTest;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class PricesApiApplicationTests {

	@Test
	void contextLoads() {
		assertThat(PricesApiApplication.class).isNotNull();
	}

}
