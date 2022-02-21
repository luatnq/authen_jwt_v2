package org.squad3.library.gateway.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


@Data
public class RefreshTokenReq {
    @JsonProperty("refresh_token")
    private String refreshToken;
}
