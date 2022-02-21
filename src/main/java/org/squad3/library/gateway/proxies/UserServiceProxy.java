package org.squad3.library.gateway.proxies;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.squad3.library.gateway.model.dtos.UserDTO;
import org.squad3.library.gateway.model.response.SystemResponse;

@FeignClient(name="UserService", url= "http://library-user-service:80/api/v1/users")
public interface UserServiceProxy {
    @PostMapping
    SystemResponse<UserDTO> createUser(@RequestBody UserDTO userDTO);

    @GetMapping("/*/accounts")
    SystemResponse<UserDTO> getUserByLoginId(@RequestParam(name = "login_id") String loginId);
}
