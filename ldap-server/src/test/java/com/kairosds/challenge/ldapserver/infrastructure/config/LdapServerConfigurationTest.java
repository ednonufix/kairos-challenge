package com.kairosds.challenge.ldapserver.infrastructure.config;

import com.kairosds.challenge.ldapserver.infrastructure.adapter.ldap.LdapServerProperties;

import org.springframework.core.io.Resource;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LdapServerConfigurationTest {

	private @Mock LdapServerProperties properties;
	private @Mock Resource schema;
	private @InjectMocks LdapServerConfiguration ldapServerConfiguration;

	@BeforeEach
	void setUp() throws IOException {
		when(properties.port()).thenReturn(389);
		when(properties.base()).thenReturn("dc=example,dc=com");
		when(properties.password()).thenReturn("secret");
		when(schema.getFile()).thenReturn(Path.of("src/test/resources/schema.ldif").toFile());
	}

	@Test
	void runSuccessfully() {
		assertDoesNotThrow(() -> ldapServerConfiguration.inMemoryDirectoryServer());
	}

	@Test
	void runFailure() {
		when(properties.base()).thenReturn("dc=failure");
		var msg = "Unable to add entry 'dc=example,dc=com' because it is not within any of the base DNs configured in the server.";
		assertThatExceptionOfType(Exception.class).isThrownBy(() -> ldapServerConfiguration.inMemoryDirectoryServer()).withMessage(msg);
	}

}