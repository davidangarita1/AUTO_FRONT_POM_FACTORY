package com.turnos.automation.pages;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.support.FindBy;

public class NavbarComponent extends PageObject {

    @FindBy(xpath = "//button[normalize-space(text())='Cerrar sesión']")
    private WebElementFacade signOutButton;

    public boolean isSignOutButtonVisible() {
        return signOutButton.isVisible();
    }
}
