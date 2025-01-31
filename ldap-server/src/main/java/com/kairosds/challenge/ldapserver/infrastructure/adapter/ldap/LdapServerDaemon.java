package com.kairosds.challenge.ldapserver.infrastructure.adapter.ldap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

import com.unboundid.ldap.listener.InMemoryDirectoryServer;
import com.unboundid.ldap.listener.InMemoryDirectoryServerConfig;
import com.unboundid.ldap.listener.InMemoryListenerConfig;

@Slf4j
@EnableConfigurationProperties(LdapServerProperties.class)
@Component
public class LdapServerDaemon implements CommandLineRunner {

	private final LdapServerProperties properties;
	private final Resource schema;

	public LdapServerDaemon(LdapServerProperties properties, @Value("classpath:schema.ldif") Resource schema) {
		this.properties = properties;
		this.schema = schema;
	}

	@Override
	public void run(String... args) throws Exception {
		var listener = InMemoryListenerConfig.createLDAPConfig("ldap", properties.port());
		var config = new InMemoryDirectoryServerConfig(properties.base());
		config.addAdditionalBindCredentials("cn=manager", properties.password());
		config.setListenerConfigs(listener);
		config.setReferentialIntegrityAttributes("member", "uniqueMember");

		var ds = new InMemoryDirectoryServer(config);
		ds.importFromLDIF(true, schema.getFile());
		ds.startListening();
		log.info("LDAP Server ready at port {}", properties.port());
	}
}
