package com.turnos.automation.pages;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.support.FindBy;

public class NavbarComponent extends PageObject {

    @FindBy(xpath = "//button[normalize-space(text())='Cerrar sesión']")
    private WebElementFacade signOutButton;

    @FindBy(xpath = "//a[normalize-space(text())='Gestión Médicos']")
    private WebElementFacade doctorsLink;

    public boolean isSignOutButtonVisible() {
        return signOutButton.isVisible();
    }

    public boolean isDoctorsLinkVisible() {
        return doctorsLink.isVisible();
    }

    public void clickDoctorsLink() {
        doctorsLink.click();
    }

    public boolean isLinkVisible(String linkText) {
        return find(org.openqa.selenium.By.xpath(
                "//a[normalize-space(text())='" + linkText + "']")).isVisible();
    }

    public void clickLink(String linkText) {
        find(org.openqa.selenium.By.xpath(
                "//a[normalize-space(text())='" + linkText + "']")).click();
    }
}
