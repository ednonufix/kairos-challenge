package com.kairosds.challenge.pricesapi.application.query.handle;

import com.kairosds.challenge.pricesapi.application.query.dto.AuthenticationDetail;

public interface AuthenticationQueryHandler {
	AuthenticationDetail authenticationDetail(String code);
}
