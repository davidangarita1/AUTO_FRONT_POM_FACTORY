package com.turnos.automation.pages;

import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NavbarComponent extends PageObject {

    @FindBy(linkText = "Cerrar sesión")
    private WebElement linkCerrarSesion;

    @FindBy(linkText = "Iniciar sesión")
    private WebElement linkIniciarSesion;

    @FindBy(linkText = "Registrarse")
    private WebElement linkRegistrarse;

    public boolean estaVisibleCerrarSesion() {
        return linkCerrarSesion.isDisplayed();
    }

    public boolean estaVisibleIniciarSesion() {
        return linkIniciarSesion.isDisplayed();
    }

    public boolean estaVisibleRegistrarse() {
        return linkRegistrarse.isDisplayed();
    }
}
