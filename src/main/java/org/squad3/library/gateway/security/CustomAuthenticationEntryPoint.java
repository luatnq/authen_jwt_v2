package org.squad3.library.gateway.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Clock;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException e) throws IOException, ServletException {
        ObjectMapper oMapper = new ObjectMapper();
        Map<String, Object> errors = new HashMap<>();
        Clock clock = Clock.systemDefaultZone();
        Instant instant = clock.instant();

        errors.put("timestamp", instant.toString());
        errors.put("message", "Missing tokens");

        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(401);
        response.getWriter().write(oMapper.writeValueAsString(errors));
    }
}
