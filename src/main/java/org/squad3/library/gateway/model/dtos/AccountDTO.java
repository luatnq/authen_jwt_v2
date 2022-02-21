package org.squad3.library.gateway.model.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountDTO {

    @NotBlank(message = "The username must not be null.")
    @Length(max = 20, message = "Max length of username is 20 characters.")
    private String username;

    @NotBlank(message = "The password must not be null.")
    private String password;
}
