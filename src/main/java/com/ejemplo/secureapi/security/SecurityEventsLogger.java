package com.ejemplo.secureapi.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

@Component
public class SecurityEventsLogger {

    private static final Logger log = LoggerFactory.getLogger("SECURITY_EVENTS");

    @EventListener
    public void onSuccess(AuthenticationSuccessEvent event) {
        log.info("Login exitoso: {}", event.getAuthentication().getName());
    }

    @EventListener
    public void onFailure(AbstractAuthenticationFailureEvent event) {
        log.warn("Login fallido para '{}': {}",
                event.getAuthentication().getName(), event.getException().getMessage());
    }
}