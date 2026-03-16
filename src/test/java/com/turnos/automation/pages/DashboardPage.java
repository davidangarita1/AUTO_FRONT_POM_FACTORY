package com.turnos.automation.pages;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.FindBy;

public class DashboardPage extends PageObject {

    @FindBy(css = "h1, h2, [data-testid='dashboard-title']")
    private WebElementFacade tituloDashboard;

    public boolean estaCargada() {
        waitFor(ExpectedConditions.urlContains("/dashboard"));
        return getDriver().getCurrentUrl().contains("/dashboard");
    }

    public String obtenerUrl() {
        return getDriver().getCurrentUrl();
    }
}
