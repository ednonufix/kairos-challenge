package com.kairosds.challenge.ldapserver;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;

import ch.qos.logback.core.testUtil.RandomUtil;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class LdapServerApplicationTests {

	private static final Integer LDAP_PORT = RandomUtil.getRandomServerPort();

	@DynamicPropertySource
	static void properties(DynamicPropertyRegistry registry) {
		registry.add("ldap.base", () -> "dc=example,dc=com");
		registry.add("ldap.port", () -> LDAP_PORT);
		registry.add("ldap.password", () -> "secret");
	}

	@Test
	void contextLoads() {
		assertThat(LdapServerApplication.class).isNotNull();
	}

}
