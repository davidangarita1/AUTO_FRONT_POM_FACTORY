package com.turnos.automation.hooks;

import com.turnos.automation.util.TestConstants;
import io.cucumber.java.Before;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class UserSetup {

    private static final int STATUS_CREATED = 201;
    private static final int STATUS_CONFLICT = 409;

    @Before("@crear_usuario")
    public void createTestUser() throws Exception {
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

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        int status = response.statusCode();

        if (status != STATUS_CREATED && status != STATUS_CONFLICT) {
            throw new IllegalStateException(
                "Failed to set up test user. HTTP " + status + ": " + response.body()
            );
        }
    }
}
