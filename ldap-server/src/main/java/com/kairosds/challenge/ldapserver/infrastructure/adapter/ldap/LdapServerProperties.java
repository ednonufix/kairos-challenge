package com.kairosds.challenge.ldapserver.infrastructure.adapter.ldap;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "ldap")
public record LdapServerProperties(String base, Integer port, String password) {

}
