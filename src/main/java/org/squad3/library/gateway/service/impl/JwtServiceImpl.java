package org.squad3.library.gateway.service.impl;

import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.squad3.library.gateway.model.dtos.AccountDTO;
import org.squad3.library.gateway.model.dtos.UserDTO;
import org.squad3.library.gateway.service.JwtService;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@Log4j2
public class JwtServiceImpl implements JwtService {

    @Value("${app.jwtSecret.token}")
    private String JWT_SECRET_TOKEN;
    @Value("${app.jwtExpirationInMs.token}")
    private long JWT_EXPIRATION_TOKEN;
    @Value("${app.role.reader}")
    private String ROLE_READER;
    @Value("${app.role.admin}")
    private String ROLE_ADMIN;
    @Value("${app.token.type}")
    private String TOKEN_TYPE;
    @Value("${app.token.role.reader}")
    private String ROLE_TOKEN_READER;

    public String generateAccessToken(UserDTO userDTO) {
        return Jwts.builder()
                .setHeader(header())
                .signWith(SignatureAlgorithm.HS256, JWT_SECRET_TOKEN)
                .setClaims(getClaims(userDTO))
                .setSubject(userDTO.getAccount().getUsername())
                .setExpiration(getExpiredTokenTime())
                .setIssuedAt(getTimeNow())
                .compact();
    }

    public String generateRefreshToken(AccountDTO accountDTO){
        String username = accountDTO.getUsername();
        return Jwts.builder()
                .setClaims(this.stringRandomGenerator())
                .setSubject(username)
                .signWith(SignatureAlgorithm.HS256, JWT_SECRET_TOKEN)
                .compact();
    }

    public String getUsernameFromSubjectJWT(String token) {
        return getAllClaimsFromToken(token).getSubject();
    }

    public Claims getAllClaimsFromToken(String token){
        return Jwts.parser()
                .setSigningKey(JWT_SECRET_TOKEN)
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(JWT_SECRET_TOKEN).parseClaimsJws(authToken);
            return true;
        } catch (MalformedJwtException ex) {
            log.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            log.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            log.error("JWT claims string is empty.");
        }
        return false;
    }
    public String getRoleUserFromToken(String accessToken){
        String role = this.getAllClaimsFromToken(accessToken).get("role").toString();
        if (role.equals(ROLE_TOKEN_READER)){
            return ROLE_READER;
        }else {
            return ROLE_ADMIN;
        }
    }
    private Date getTimeNow(){
        return new Date();
    }

    private Date getExpiredTokenTime(){
        return new Date(System.currentTimeMillis() + JWT_EXPIRATION_TOKEN);
    }

    private Map<String, Object> getClaims(UserDTO userDTO){
        Map<String, Object> mClaims = new HashMap<>();
        mClaims.put("email", userDTO.getEmail());
        mClaims.put("phone_number", userDTO.getPhone());
        mClaims.put("role", userDTO.getRole());
        mClaims.put("full_name",userDTO.getName());
        mClaims.put("username", userDTO.getAccount().getUsername());
        return mClaims;
    }

    private Map<String, Object> header(){
        Map<String, Object> map = new HashMap<>();
        map.put("typ", TOKEN_TYPE);
        return map;
    }

    private Map<String, Object> stringRandomGenerator(){
        Map<String, Object> mClaims = new HashMap<>();
        String randomString = RandomStringUtils.randomAlphabetic(60);
        mClaims.put("info", randomString);
        return mClaims;
    }
}
