package com.kairosds.challenge.ldapserver.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PasswordEncoder {
	public static void main(String[] args) {
		var password = "edwin";
		var passwordEncoder = new BCryptPasswordEncoder();
		log.info("Password: {}", passwordEncoder.encode(password));
	}
}
