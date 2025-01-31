package com.kairosds.challenge.oauthserver;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class OauthServerApplicationTests {

	private static KeyPair keyPair;

	@BeforeAll
	static void setUp() throws NoSuchAlgorithmException {
		var generator = KeyPairGenerator.getInstance("RSA");
		generator.initialize(2048);
		keyPair = generator.generateKeyPair();
	}

	@DynamicPropertySource
	static void properties(DynamicPropertyRegistry registry) {
		var privateKeyPEM = getPrivateKeyPEM();
		var publicKeyPEM = getPublicKeyPEM();
		registry.add("jwt.public", () -> publicKeyPEM);
		registry.add("jwt.private", () -> privateKeyPEM);
	}

	@Test
	void contextLoads() {
		assertThat(OauthServerApplication.class).isNotNull();
	}

	private static String getPublicKeyPEM() {
		return """
									-----BEGIN PUBLIC KEY-----
									%s
									-----END PUBLIC KEY-----
									""".formatted(Base64.getMimeEncoder(64, "\n".getBytes()).encodeToString(keyPair.getPublic().getEncoded()));
	}

	private static String getPrivateKeyPEM() {
		return """
									-----BEGIN PRIVATE KEY-----
									%s
									-----END PRIVATE KEY-----
									""".formatted(Base64.getMimeEncoder(64, "\n".getBytes()).encodeToString(keyPair.getPrivate().getEncoded()));
	}

}
