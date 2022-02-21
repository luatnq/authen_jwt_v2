package org.squad3.library.gateway.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.squad3.library.gateway.model.dtos.AccountDTO;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterReqDTO {

    @Length(max = 30, message = "Max length of name is 30 characters.")
    private String name;

    @NotBlank(message = "The email must not be null.")
    @Email(message = "The email invalid form.")
    private String email;

    private String phone;

    private AccountDTO account;

    private String role;
}
