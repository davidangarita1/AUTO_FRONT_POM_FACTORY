package com.turnos.automation.pages;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.support.FindBy;

public class NavbarComponent extends PageObject {

    @FindBy(linkText = "Cerrar sesión")
    private WebElementFacade linkCerrarSesion;

    @FindBy(linkText = "Iniciar sesión")
    private WebElementFacade linkIniciarSesion;

    @FindBy(linkText = "Registrarse")
    private WebElementFacade linkRegistrarse;

    public boolean estaVisibleCerrarSesion() {
        return linkCerrarSesion.isVisible();
    }

    public boolean estaVisibleIniciarSesion() {
        return linkIniciarSesion.isVisible();
    }

    public boolean estaVisibleRegistrarse() {
        return linkRegistrarse.isVisible();
    }
}
