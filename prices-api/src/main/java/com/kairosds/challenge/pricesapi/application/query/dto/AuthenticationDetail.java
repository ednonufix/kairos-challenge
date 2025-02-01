package com.kairosds.challenge.pricesapi.application.query.dto;

public record AuthenticationDetail(String access_token, String refresh_token, String scope, String id_token, String token_type,
int expires_in) {
}
