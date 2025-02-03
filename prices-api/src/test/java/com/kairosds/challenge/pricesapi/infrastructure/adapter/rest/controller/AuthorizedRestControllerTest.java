package com.kairosds.challenge.pricesapi.infrastructure.adapter.rest.controller;

import com.kairosds.challenge.pricesapi.application.query.dto.AuthenticationDetail;
import com.kairosds.challenge.pricesapi.application.query.handle.AuthenticationQueryHandler;
import com.kairosds.challenge.pricesapi.infrastructure.adapter.rest.ui.AuthorizedRestUI;
import com.kairosds.challenge.pricesapi.infrastructure.config.SecurityConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.when;

@WebMvcTest(AuthorizedRestUI.class)
@Import(SecurityConfig.class)
class AuthorizedRestControllerTest {

	private static final String URL = "/v1/authorized";
	private @MockitoBean ClientRegistrationRepository clientRegistrationRepository;
	private @MockitoBean JwtDecoder jwtDecoder;
	private @MockitoBean AuthenticationQueryHandler authenticationQueryHandler;
	private @Autowired MockMvc mockMvc;

	@Test
	void authenticationDetail() throws Exception {
		var code = Instancio.create(String.class);
		var response = Instancio.create(AuthenticationDetail.class);
		when(authenticationQueryHandler.authenticationDetail(code)).thenReturn(response);
		mockMvc.perform(get(URL).queryParam("code", code)).andExpect(status().isOk());
	}

}