package com.turnos.automation.steps;

import com.turnos.automation.pages.SignUpPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.assertj.core.api.Assertions.assertThat;

public class SignupStepDefinitions {

    private SignUpPage signUpPage;

    @Given("el usuario se encuentra en la pagina de registro")
    public void userIsOnSignUpPage() {
        signUpPage.open();
    }

    @When("ingresa el nombre {string} el email {string} y la contrasena {string}")
    public void userEntersNameEmailAndPassword(String name, String email, String password) {
        signUpPage.enterName(name);
        signUpPage.enterEmail(email);
        signUpPage.enterPassword(password);
    }

    @And("hace clic en el boton de registrarse")
    public void userClicksSignUpButton() {
        signUpPage.clickSignUp();
    }

    @Then("el sistema muestra un mensaje de error indicando que la contrasena es debil")
    public void systemShowsWeakPasswordError() {
        assertThat(signUpPage.isErrorMessageVisible())
                .as("Debe mostrarse un mensaje de error por contraseña débil")
                .isTrue();
        assertThat(signUpPage.getErrorMessage())
                .as("El mensaje de error debe indicar los requisitos de contraseña")
                .contains("contraseña");
    }

    @And("el usuario permanece en la pagina de registro")
    public void userRemainsOnSignUpPage() {
        assertThat(signUpPage.isOnSignUpPage())
                .as("La URL debe contener /signup")
                .isTrue();
    }
}
