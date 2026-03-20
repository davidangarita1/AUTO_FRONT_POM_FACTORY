package com.turnos.automation.hooks;

import com.turnos.automation.util.Constants;
import io.cucumber.java.Before;
import net.serenitybdd.model.environment.EnvironmentSpecificConfiguration;
import net.thucydides.model.environment.SystemEnvironmentVariables;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class UserSetupHook {

    private static final int STATUS_CREATED = 201;
    private static final int STATUS_CONFLICT = 409;

    @Before("@crear_usuario")
    public void createTestUser() throws Exception {
        String apiBaseUrl = EnvironmentSpecificConfiguration
                .from(SystemEnvironmentVariables.currentEnvironmentVariables())
                .getProperty("api.base.url");

        HttpClient client = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_1_1)
            .build();
        String body = String.format(
            "{\"email\":\"%s\",\"password\":\"%s\",\"nombre\":\"%s\",\"rol\":\"%s\"}",
            Constants.TEST_USER_EMAIL,
            Constants.TEST_USER_PASSWORD,
            Constants.TEST_USER_NAME,
            Constants.TEST_USER_ROLE
        );

        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(apiBaseUrl + Constants.ENDPOINT_SIGN_UP))
            .header("Content-Type", Constants.CONTENT_TYPE_JSON)
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
