package com.ejemplo.secureapi.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class DemoController {

    @GetMapping("/hello")   // cualquier usuario con token
    public Map<String, String> hello(Authentication auth) {
        return Map.of("mensaje", "Hola " + auth.getName() + ", accediste a un endpoint protegido");
    }

    @GetMapping("/admin/panel")   // solo rol ADMIN
    public Map<String, String> admin() {
        return Map.of("mensaje", "Bienvenido al panel de administrador");
    }
}