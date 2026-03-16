package com.turnos.automation.pages;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.support.FindBy;

public class NavbarComponent extends PageObject {

    @FindBy(xpath = "//button[normalize-space(text())='Cerrar sesión']")
    private WebElementFacade signOutButton;

    @FindBy(linkText = "Iniciar sesión")
    private WebElementFacade signInLink;

    @FindBy(linkText = "Registrarse")
    private WebElementFacade signUpLink;

    public boolean isSignOutButtonVisible() {
        return signOutButton.isVisible();
    }

    public boolean isSignInLinkVisible() {
        return signInLink.isVisible();
    }

    public boolean isSignUpLinkVisible() {
        return signUpLink.isVisible();
    }
}
