package org.squad3.library.gateway.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class LoginResDTO implements Serializable {

    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("type")
    private String type;

    @JsonProperty("refresh_token")
    private String refreshToken;

    public LoginResDTO(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.type = "Bearer";
    }
}
