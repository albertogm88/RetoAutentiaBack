package com.reto.autentia.configuration;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import com.reto.autentia.control.CursoController;
import com.reto.autentia.control.NivelController;
import com.reto.autentia.control.ProfesoresController;

@Component
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        registerEndpoints();
    }
    private void registerEndpoints() {
        register(CursoController.class);
        register(NivelController.class);
        register(ProfesoresController.class);
    }
}