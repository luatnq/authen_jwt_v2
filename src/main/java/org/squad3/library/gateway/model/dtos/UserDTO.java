package org.squad3.library.gateway.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.squad3.library.gateway.model.request.RegisterReqDTO;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
//import org.hibernate.validator.constraints.Length;
//
//import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {

    @Length(max = 30, message = "Max length of name is 30 characters.")
    private String name;

    @NotBlank(message = "The email must not be null.")
    @Email(message = "The email invalid form.")
    private String email;

    @Length(min = 10, max = 10, message = "The number phone must have 10 digits")
    private String phone;

    private AccountDTO account;

    private String role;

    public UserDTO(RegisterReqDTO registerReqDTO, String encryptedPassword, String role){
        this.account = new AccountDTO();
        this.account.setUsername(registerReqDTO.getAccount().getUsername());
        this.account.setPassword(encryptedPassword);
        this.name = registerReqDTO.getName();
        this.email = registerReqDTO.getEmail();
        this.phone = registerReqDTO.getPhone();
        this.role = role;
    }
    @Override
    public String toString() {
        return "UserDTO{" +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", account=" + account +
                ", role='" + role + '\'' +
                '}';
    }
}
