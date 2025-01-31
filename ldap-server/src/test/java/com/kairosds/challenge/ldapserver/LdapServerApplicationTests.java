package com.kairosds.challenge.ldapserver;

import org.springframework.boot.test.context.SpringBootTest;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class LdapServerApplicationTests {

	@Test
	void contextLoads() {
		assertThat(LdapServerApplication.class).isNotNull();
	}

}
