package com.kairosds.challenge.ldapserver.infrastructure.adapter.ldap;

import com.unboundid.ldap.listener.InMemoryDirectoryServer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LdapServerDaemonTest {

	private @Mock InMemoryDirectoryServer inMemoryDirectoryServer;
	private @InjectMocks LdapServerDaemon ldapServerDaemon;

	@Test
	void runSuccessfully() {
		when(inMemoryDirectoryServer.getListenPort()).thenReturn(0);
		assertDoesNotThrow(() -> ldapServerDaemon.run());
	}

}