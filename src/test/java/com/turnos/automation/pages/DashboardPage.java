package com.turnos.automation.pages;

import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DashboardPage extends PageObject {

    public boolean isLoaded() {
        waitFor(ExpectedConditions.urlContains("/dashboard"));
        return getDriver().getCurrentUrl().contains("/dashboard");
    }
}
