package org.squad3.library.gateway.service;

import org.squad3.library.gateway.model.dtos.AccountDTO;
import org.squad3.library.gateway.model.dtos.UserDTO;
import org.squad3.library.gateway.model.request.RegisterReqDTO;
import org.squad3.library.gateway.model.response.LoginResDTO;
import org.squad3.library.gateway.model.response.SystemResponse;

public interface AuthenticationService {
    UserDTO register(RegisterReqDTO registerReqDTO);

    LoginResDTO login(AccountDTO accountDTO);
}
