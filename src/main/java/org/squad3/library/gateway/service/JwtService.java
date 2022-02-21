package org.squad3.library.gateway.service;

import io.jsonwebtoken.Claims;
import org.squad3.library.gateway.model.dtos.AccountDTO;
import org.squad3.library.gateway.model.dtos.UserDTO;

public interface JwtService {
    String generateAccessToken(UserDTO userDTO);
    String generateRefreshToken(AccountDTO accountDTO);
    String getUsernameFromSubjectJWT(String token);
    Claims getAllClaimsFromToken(String token);
    String getRoleUserFromToken(String accessToken);
    boolean validateToken(String authToken);
}
