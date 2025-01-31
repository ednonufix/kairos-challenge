package com.kairosds.challenge.ldapserver.infrastructure.config;

import com.kairosds.challenge.ldapserver.infrastructure.adapter.ldap.LdapServerProperties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.io.IOException;

import com.unboundid.ldap.listener.InMemoryDirectoryServer;
import com.unboundid.ldap.listener.InMemoryDirectoryServerConfig;
import com.unboundid.ldap.listener.InMemoryListenerConfig;
import com.unboundid.ldap.sdk.LDAPException;

@Configuration
@EnableConfigurationProperties(LdapServerProperties.class)
public class LdapServerConfiguration {

	private final LdapServerProperties properties;
	private final Resource schema;

	public LdapServerConfiguration(LdapServerProperties properties, @Value("classpath:schema.ldif") Resource schema) {
		this.properties = properties;
		this.schema = schema;
	}

	@Bean
	InMemoryDirectoryServer inMemoryDirectoryServer() throws LDAPException, IOException {
		var listener = InMemoryListenerConfig.createLDAPConfig("ldap", properties.port());
		var config = new InMemoryDirectoryServerConfig(properties.base());
		config.addAdditionalBindCredentials("cn=manager", properties.password());
		config.setListenerConfigs(listener);
		config.setReferentialIntegrityAttributes("member", "uniqueMember");

		try (var ds = new InMemoryDirectoryServer(config)) {
			ds.importFromLDIF(true, schema.getFile());
			return ds;
		}
	}
}
