package com.turnos.automation.hooks;

import com.turnos.automation.util.TestConstants;
import io.cucumber.java.Before;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class UserSetup {

    @Before("@crear_usuario")
    public void createTestUser() {
        HttpClient client = HttpClient.newHttpClient();
        String body = String.format(
            "{\"email\":\"%s\",\"password\":\"%s\",\"nombre\":\"%s\",\"rol\":\"%s\"}",
            TestConstants.TEST_USER_EMAIL,
            TestConstants.TEST_USER_PASSWORD,
            TestConstants.TEST_USER_NAME,
            TestConstants.TEST_USER_ROLE
        );

        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(TestConstants.API_BASE_URL + TestConstants.ENDPOINT_SIGN_UP))
            .header("Content-Type", TestConstants.CONTENT_TYPE_JSON)
            .POST(HttpRequest.BodyPublishers.ofString(body))
            .build();

        try {
            client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception ignored) {
        }
    }
}
