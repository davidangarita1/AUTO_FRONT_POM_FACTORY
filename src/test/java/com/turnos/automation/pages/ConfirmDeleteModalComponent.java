package com.turnos.automation.pages;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ConfirmDeleteModalComponent extends PageObject {

    @FindBy(css = "[data-testid='confirm-delete-overlay']")
    private WebElementFacade modalOverlay;

    @FindBy(css = "[data-testid='confirm-delete-overlay'] h2")
    private WebElementFacade modalTitle;

    @FindBy(css = "[data-testid='confirm-delete-overlay'] p")
    private WebElementFacade confirmationMessage;

    @FindBy(xpath = "//div[@data-testid='confirm-delete-overlay']//button[normalize-space(text())='Cancelar']")
    private WebElementFacade cancelButton;

    @FindBy(xpath = "//div[@data-testid='confirm-delete-overlay']//button[normalize-space(text())='Aceptar']")
    private WebElementFacade confirmButton;

    public boolean isModalVisible() {
        return modalOverlay.isVisible();
    }

    public void waitForModal() {
        waitFor(ExpectedConditions.visibilityOf(modalOverlay.getWrappedElement()));
    }

    public String getConfirmationMessage() {
        return confirmationMessage.getText();
    }

    public void clickCancel() {
        cancelButton.click();
    }

    public void clickConfirm() {
        confirmButton.click();
    }
}
