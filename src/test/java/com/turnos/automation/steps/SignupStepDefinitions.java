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
    public void elUsuarioSeEncuentraEnLaPaginaDeRegistro() {
        signUpPage.open();
    }

    @When("ingresa el nombre {string} el email {string} y la contrasena {string}")
    public void ingresaElNombreElEmailYLaContrasena(String nombre, String email, String contrasena) {
        signUpPage.ingresarNombre(nombre);
        signUpPage.ingresarEmail(email);
        signUpPage.ingresarContrasena(contrasena);
    }

    @And("hace clic en el boton de registrarse")
    public void hacerClicEnElBotonDeRegistrarse() {
        signUpPage.hacerClicEnRegistrarse();
    }

    @Then("el sistema muestra un mensaje de error indicando que la contrasena es debil")
    public void elSistemaMuestraUnMensajeDeErrorIndicandoQuelaContrasenaEsDebil() {
        assertThat(signUpPage.estaVisibleMensajeError())
                .as("Debe mostrarse un mensaje de error por contraseña débil")
                .isTrue();
        assertThat(signUpPage.obtenerMensajeError())
                .as("El mensaje de error debe indicar los requisitos de contraseña")
                .contains("contraseña");
    }

    @And("el usuario permanece en la pagina de registro")
    public void elUsuarioPermaneneEnLaPaginaDeRegistro() {
        assertThat(signUpPage.estaEnPaginaDeRegistro())
                .as("La URL debe contener /signup")
                .isTrue();
    }
}
