package com.ejemplo.secureapi.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAuthEntryPoint implements AuthenticationEntryPoint {

    private static final Logger log = LoggerFactory.getLogger(CustomAuthEntryPoint.class);

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException ex) throws IOException {
        String detalle = request.getAttribute("jwt_error") != null
                ? "Token inválido o expirado"
                : "Se requiere un token de autenticación (Bearer)";
        log.warn("401 Unauthorized en {} desde {}: {}",
                request.getRequestURI(), request.getRemoteAddr(), detalle);
        ErrorResponseWriter.write(response, 401, "Unauthorized", detalle, request.getRequestURI());
    }
}