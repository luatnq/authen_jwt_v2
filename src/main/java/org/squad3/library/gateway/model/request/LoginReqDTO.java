package org.squad3.library.gateway.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginReqDTO {
    @NotBlank(message = "The username must not be null.")
    private String username;

    @NotBlank(message = "The password must not be null.")
    private String password;
}
