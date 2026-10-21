package com.ejemplo.secureapi.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    private static final Logger log = LoggerFactory.getLogger(CustomAccessDeniedHandler.class);

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException ex) throws IOException {
        String usuario = request.getUserPrincipal() != null
                ? request.getUserPrincipal().getName() : "desconocido";
        log.warn("403 Forbidden: usuario '{}' intentó acceder a {}", usuario, request.getRequestURI());
        ErrorResponseWriter.write(response, 403, "Forbidden",
                "No tienes permisos suficientes para este recurso", request.getRequestURI());
    }
}