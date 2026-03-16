package com.turnos.automation.pages;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.annotations.DefaultUrl;
import org.openqa.selenium.support.FindBy;

@DefaultUrl("/signup")
public class SignUpPage extends PageObject {

    @FindBy(css = "input[type='text'][placeholder='Nombre']")
    private WebElementFacade campoNombre;

    @FindBy(css = "input[type='email'][placeholder='Email']")
    private WebElementFacade campoEmail;

    @FindBy(css = "input[type='password'][placeholder='Contraseña']")
    private WebElementFacade campoContrasena;

    @FindBy(css = "button[type='submit']")
    private WebElementFacade botonRegistrarse;

    @FindBy(css = "[role='alert']")
    private WebElementFacade mensajeError;

    public void ingresarNombre(String nombre) {
        campoNombre.clear();
        campoNombre.sendKeys(nombre);
    }

    public void ingresarEmail(String email) {
        campoEmail.clear();
        campoEmail.sendKeys(email);
    }

    public void ingresarContrasena(String contrasena) {
        campoContrasena.clear();
        campoContrasena.sendKeys(contrasena);
    }

    public void hacerClicEnRegistrarse() {
        botonRegistrarse.click();
    }

    public String obtenerMensajeError() {
        return mensajeError.getText();
    }

    public boolean estaVisibleMensajeError() {
        return mensajeError.isVisible();
    }

    public boolean estaEnPaginaDeRegistro() {
        return getDriver().getCurrentUrl().contains("/signup");
    }
}
