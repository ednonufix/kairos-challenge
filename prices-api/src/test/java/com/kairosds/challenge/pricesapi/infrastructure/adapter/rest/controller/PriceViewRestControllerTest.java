package com.kairosds.challenge.pricesapi.infrastructure.adapter.rest.controller;

import com.kairosds.challenge.pricesapi.application.query.handle.PriceViewQueryHandler;
import com.kairosds.challenge.pricesapi.domain.dto.PriceView;
import com.kairosds.challenge.pricesapi.infrastructure.adapter.rest.mapper.PriceViewResponseMapper;
import com.kairosds.challenge.pricesapi.infrastructure.adapter.rest.request.PriceViewFindRequest;
import com.kairosds.challenge.pricesapi.infrastructure.adapter.rest.response.PriceViewFindResponse;
import com.kairosds.challenge.pricesapi.infrastructure.adapter.rest.ui.PriceViewRestUI;
import com.kairosds.challenge.pricesapi.infrastructure.config.SecurityConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest(PriceViewRestUI.class)
@Import(SecurityConfig.class)
class PriceViewRestControllerTest {

	private static final String URL = "/v1/price";
	private @MockitoBean ClientRegistrationRepository clientRegistrationRepository;
	private @MockitoBean JwtDecoder jwtDecoder;
	private @MockitoBean PriceViewQueryHandler priceViewQueryHandler;
	private @Autowired ObjectMapper mapper;
	private @Autowired MockMvc mockMvc;

	@Test
	void findPrices() throws Exception {
		var request = Instancio.create(PriceViewFindRequest.class);
		var response = Instancio.create(PriceView.class);
		when(priceViewQueryHandler.findPrice(any())).thenReturn(Optional.of(response));
		mockMvc.perform(post(URL).content(mapper.writeValueAsString(request)).contentType(MediaType.APPLICATION_JSON)
			.with(addAuthority("ROLE_USERS"))).andExpect(status().isOk()).andExpect(result -> {
			var content = mapper.readValue(result.getResponse().getContentAsString(), PriceViewFindResponse.class);
			var expected = PriceViewResponseMapper.INSTANCE.toResponse(response);
			assertThat(content).isEqualTo(expected);
		});
	}

	@Test
	void findPricesDeniedAccess() throws Exception {
		var request = Instancio.create(PriceViewFindRequest.class);
		mockMvc.perform(post(URL).content(mapper.writeValueAsString(request)).contentType(MediaType.APPLICATION_JSON)
			.with(addAuthority("ROLE_FINANCIAL"))).andExpect(status().isForbidden());
	}

	@Test
	void findPricesMalformedJson() throws Exception {
		var malformedJson = """
		           {
		             "brandId": 1,
		             "productId": 1,
		             "date": "2021-06-14T10:00:00",
		           }
		           """;
		mockMvc.perform(post(URL).content(malformedJson).contentType(MediaType.APPLICATION_JSON)
			.with(addAuthority("ROLE_USERS"))).andExpect(status().is(HttpStatus.BAD_REQUEST.value()));
	}

	@Test
	void findPricesInvalidJson() throws Exception {
		var malformedJson = """
		           {
		             "brandId": 1,
		             "productId": null,
		             "date": "2021-06-14T10:00:00"
		           }
		           """;
		mockMvc.perform(post(URL).content(malformedJson).contentType(MediaType.APPLICATION_JSON)
			.with(addAuthority("ROLE_USERS"))).andExpect(status().is(HttpStatus.BAD_REQUEST.value()));
	}

	private static SecurityMockMvcRequestPostProcessors.JwtRequestPostProcessor addAuthority(String role) {
		return jwt().authorities(new SimpleGrantedAuthority(role));
	}

}