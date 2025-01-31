package com.kairosds.challenge.ldapserver.infrastructure.adapter.ldap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.unboundid.ldap.listener.InMemoryDirectoryServer;

@Slf4j
@RequiredArgsConstructor
@Component
public class LdapServerDaemon implements CommandLineRunner {

	private final InMemoryDirectoryServer inMemoryDirectoryServer;

	@Override
	public void run(String... args) throws Exception {
		inMemoryDirectoryServer.startListening();
		log.info("LDAP Server ready at port {}", inMemoryDirectoryServer.getListenPort());
	}
}
