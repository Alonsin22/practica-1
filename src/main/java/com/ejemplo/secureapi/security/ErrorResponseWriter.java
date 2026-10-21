package com.ejemplo.secureapi.security;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Instant;

public final class ErrorResponseWriter {

    private ErrorResponseWriter() {}

    public static void write(HttpServletResponse response, int status, String error,
                             String message, String path) throws IOException {
        response.setStatus(status);
        response.setContentType("application/json;charset=UTF-8");
        String json = String.format(
            "{\"timestamp\":\"%s\",\"status\":%d,\"error\":\"%s\",\"message\":\"%s\",\"path\":\"%s\"}",
            Instant.now(), status, error, message, path);
        response.getWriter().write(json);
    }
}