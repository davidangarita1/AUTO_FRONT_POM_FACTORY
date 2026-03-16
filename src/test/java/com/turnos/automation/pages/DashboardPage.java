package com.turnos.automation.pages;

import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DashboardPage extends PageObject {

    @FindBy(css = "h1, h2, [data-testid='dashboard-title']")
    private WebElement tituloDashboard;

    public boolean estaCargada() {
        return getDriver().getCurrentUrl().contains("/dashboard");
    }

    public String obtenerUrl() {
        return getDriver().getCurrentUrl();
    }
}
