package com.kairosds.challenge.ldapserver.infrastructure.adapter.ldap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.context.ConfigurationPropertiesAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = ConfigurationPropertiesAutoConfiguration.class)
@EnableConfigurationProperties(LdapServerProperties.class)
class LdapServerPropertiesTests {

	private @Autowired LdapServerProperties properties;

	@DynamicPropertySource
	static void properties(DynamicPropertyRegistry registry) {
		registry.add("ldap.base", () -> "dc=example,dc=com");
		registry.add("ldap.port", () -> 389);
		registry.add("ldap.password", () -> "secret");
	}

	@Test
	void propertiesAreLoadedCorrectly() {
		assertThat(properties.base()).isEqualTo("dc=example,dc=com");
		assertThat(properties.port()).isEqualTo(389);
		assertThat(properties.password()).isEqualTo("secret");
	}

}