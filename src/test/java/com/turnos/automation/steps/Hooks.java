package com.turnos.automation.steps;

import io.cucumber.java.Before;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Hooks {

    private static final String API_BASE_URL = "http://localhost:3000";
    private static final String TEST_USER_EMAIL = "usuario_test@correo.com";
    private static final String TEST_USER_PASSWORD = "Test1234!";
    private static final String TEST_USER_NAME = "Usuario Test";

    @Before("@crear_usuario")
    public void crearUsuarioDePrueba() {
        HttpClient client = HttpClient.newHttpClient();
        String body = String.format(
            "{\"email\":\"%s\",\"password\":\"%s\",\"nombre\":\"%s\",\"rol\":\"empleado\"}",
            TEST_USER_EMAIL, TEST_USER_PASSWORD, TEST_USER_NAME
        );

        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(API_BASE_URL + "/auth/signUp"))
            .header("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString(body))
            .build();

        try {
            client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            // El usuario puede ya existir o la API no estar disponible; el test continua
        }
    }
}
