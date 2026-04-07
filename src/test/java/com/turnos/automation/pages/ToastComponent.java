package com.turnos.automation.pages;

import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class ToastComponent extends PageObject {

    private static final By TOAST_LOCATOR = By.xpath(
            "//*[contains(@class,'toast') or contains(@class,'Toast')]");

    public void waitForToast() {
        withTimeoutOf(Duration.ofSeconds(10)).waitFor(
                ExpectedConditions.presenceOfElementLocated(TOAST_LOCATOR));
    }

    public String getToastMessage() {
        waitForToast();
        return getDriver().findElement(TOAST_LOCATOR).getText();
    }

    public boolean containsMessage(String expectedMessage) {
        waitForToast();
        String text = getDriver().findElement(TOAST_LOCATOR).getText();
        return text.contains(expectedMessage);
    }
}
