package com.turnos.automation.steps;

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
    public void elUsuarioSeEncuentraEnLaPaginaDeInicioSesion() {
        signInPage.open();
    }

    @When("ingresa el email {string} y la contrasena {string}")
    public void ingresaElEmailYLaContrasena(String email, String contrasena) {
        signInPage.ingresarEmail(email);
        signInPage.ingresarContrasena(contrasena);
    }

    @And("hace clic en el boton de iniciar sesion")
    public void hacerClicEnElBotonDeIniciarSesion() {
        signInPage.hacerClicEnIniciarSesion();
    }

    @Then("el sistema redirige al dashboard")
    public void elSistemaRedirigAlDashboard() {
        assertThat(dashboardPage.estaCargada())
                .as("La URL debe contener /dashboard")
                .isTrue();
    }

    @And("el navbar muestra la opcion de cerrar sesion")
    public void elNavbarMuestraLaOpcionDeCerrarSesion() {
        assertThat(navbarComponent.estaVisibleCerrarSesion())
                .as("El enlace 'Cerrar sesión' debe ser visible en el navbar")
                .isTrue();
    }
}
