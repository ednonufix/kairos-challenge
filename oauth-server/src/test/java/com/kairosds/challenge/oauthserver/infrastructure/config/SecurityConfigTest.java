package com.kairosds.challenge.oauthserver.infrastructure.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.server.authorization.OAuth2TokenType;
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;

import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SecurityConfigTest {

	private static SecurityConfig securityConfig;

	@BeforeAll
	static void setUp() throws NoSuchAlgorithmException {
		var generator = KeyPairGenerator.getInstance("RSA");
		generator.initialize(512);
		var keyPair = generator.generateKeyPair();
		securityConfig = new SecurityConfig((RSAPublicKey) keyPair.getPublic(), (RSAPrivateKey) keyPair.getPrivate());
	}

	@Test
	void tokenCustomizerMatchTokenType() {

		var context = mock(JwtEncodingContext.class);
		var principal = mock(Authentication.class);
		var authorities = Set.of(new SimpleGrantedAuthority("ROLE_USER"));
		var jwtClaimsSet = JwtClaimsSet.builder();

		when(context.getPrincipal()).thenReturn(principal);
		when(context.getTokenType()).thenReturn(OAuth2TokenType.ACCESS_TOKEN);
		when(principal.getAuthorities()).thenAnswer(invocation -> authorities);
		when(context.getClaims()).thenReturn(jwtClaimsSet);

		securityConfig.tokenCustomizer().customize(context);

		assertThat(jwtClaimsSet.build().getClaims()).containsEntry("authorities", Set.of("ROLE_USER"));
	}

	@Test
	void tokenCustomizerNotMatchTokenType() {

		var context = mock(JwtEncodingContext.class);
		var principal = mock(Authentication.class);

		when(context.getPrincipal()).thenReturn(principal);
		when(context.getTokenType()).thenReturn(OAuth2TokenType.REFRESH_TOKEN);

		securityConfig.tokenCustomizer().customize(context);

		verify(context, times(0)).getClaims();
	}

}