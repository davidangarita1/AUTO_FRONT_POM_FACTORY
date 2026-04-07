package com.turnos.automation.pages;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DoctorFormModalComponent extends PageObject {

    @FindBy(css = "[data-testid='modal-backdrop']")
    private WebElementFacade modalBackdrop;

    @FindBy(id = "doctor-nombre")
    private WebElementFacade nameField;

    @FindBy(id = "doctor-cedula")
    private WebElementFacade cedulaField;

    @FindBy(id = "doctor-consultorio")
    private WebElementFacade officeSelect;

    @FindBy(id = "doctor-franja")
    private WebElementFacade shiftSelect;

    @FindBy(xpath = "//div[@data-testid='modal-backdrop']//button[normalize-space(text())='Guardar' or normalize-space(text())='Guardando...']")
    private WebElementFacade saveButton;

    @FindBy(xpath = "//div[@data-testid='modal-backdrop']//button[normalize-space(text())='Cerrar']")
    private WebElementFacade closeButton;

    public boolean isModalVisible() {
        return modalBackdrop.isVisible();
    }

    public void waitForModal() {
        waitFor(ExpectedConditions.visibilityOf(modalBackdrop.getWrappedElement()));
    }

    public boolean isNameFieldVisible() {
        return nameField.isVisible();
    }

    public boolean isCedulaFieldVisible() {
        return cedulaField.isVisible();
    }

    public boolean isOfficeSelectVisible() {
        return officeSelect.isVisible();
    }

    public boolean isShiftSelectVisible() {
        return shiftSelect.isVisible();
    }

    public void enterName(String name) {
        nameField.clear();
        nameField.sendKeys(name);
    }

    public void enterCedula(String cedula) {
        cedulaField.clear();
        cedulaField.sendKeys(cedula);
    }

    public String getCedulaValue() {
        return cedulaField.getValue();
    }

    public void selectOffice(String office) {
        officeSelect.selectByValue(office);
    }

    public void selectShift(String shift) {
        shiftSelect.waitUntilEnabled();
        shiftSelect.selectByValue(shift);
    }

    public void clickSave() {
        saveButton.click();
    }

    public void clickClose() {
        closeButton.click();
    }

    public boolean isSaveButtonEnabled() {
        return saveButton.isEnabled();
    }

    public void touchNameFieldAndLeave() {
        nameField.click();
        cedulaField.click();
    }

    public void touchCedulaFieldAndLeave() {
        cedulaField.click();
        nameField.click();
    }

    public String getValidationMessage() {
        return getDriver().findElement(By.xpath(
                "//div[@data-testid='modal-backdrop']//span[contains(@class,'error')]")).getText();
    }

    public boolean hasValidationMessage(String message) {
        return getDriver().findElements(By.xpath(
                "//div[@data-testid='modal-backdrop']//span[contains(text(),'" + message + "')]"))
                .size() > 0;
    }
}
