package com.turnos.automation.stepdefinitions;

import com.turnos.automation.pages.DashboardPage;
import com.turnos.automation.pages.NavbarComponent;
import com.turnos.automation.pages.SignInPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.assertj.core.api.Assertions.assertThat;

public class LoginStepDefinitions {

    private SignInPage signInPage;
    private DashboardPage dashboardPage;
    private NavbarComponent navbarComponent;

    @Given("el usuario se encuentra en la pagina de inicio de sesion")
    public void userIsOnSignInPage() {
        signInPage.open();
    }

    @When("ingresa el email {string} y la contrasena {string}")
    public void userEntersEmailAndPassword(String email, String password) {
        signInPage.enterEmail(email);
        signInPage.enterPassword(password);
    }

    @And("hace clic en el boton de iniciar sesion")
    public void userClicksSignInButton() {
        signInPage.clickSignIn();
    }

    @Then("el sistema redirige al dashboard")
    public void systemRedirectsToDashboard() {
        assertThat(dashboardPage.isLoaded())
                .as("La URL debe contener /dashboard")
                .isTrue();
    }

    @And("el navbar muestra la opcion de cerrar sesion")
    public void navbarShowsSignOutOption() {
        assertThat(navbarComponent.isSignOutButtonVisible())
                .as("El enlace 'Cerrar sesión' debe ser visible en el navbar")
                .isTrue();
    }

    @Then("el sistema muestra un mensaje de error de {string}")
    public void systemShowsErrorMessage(String expectedMessage) {
        assertThat(signInPage.getErrorMessage())
                .as("El mensaje de error debe contener: " + expectedMessage)
                .contains(expectedMessage);
    }
}
