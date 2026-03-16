package com.turnos.automation.pages;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.annotations.DefaultUrl;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@DefaultUrl("/signin")
public class SignInPage extends PageObject {

    @FindBy(css = "input[type='email'][placeholder='Email']")
    private WebElement campoEmail;

    @FindBy(css = "input[type='password'][placeholder='Contraseña']")
    private WebElement campoContrasena;

    @FindBy(css = "button[type='submit']")
    private WebElement botonIniciarSesion;

    @FindBy(css = "[role='alert']")
    private WebElement mensajeError;

    public void ingresarEmail(String email) {
        campoEmail.clear();
        campoEmail.sendKeys(email);
    }

    public void ingresarContrasena(String contrasena) {
        campoContrasena.clear();
        campoContrasena.sendKeys(contrasena);
    }

    public void hacerClicEnIniciarSesion() {
        botonIniciarSesion.click();
    }

    public String obtenerMensajeError() {
        return mensajeError.getText();
    }

    public boolean estaVisibleMensajeError() {
        return mensajeError.isDisplayed();
    }
}
