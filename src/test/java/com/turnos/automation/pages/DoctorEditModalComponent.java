package com.turnos.automation.pages;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DoctorEditModalComponent extends PageObject {

    @FindBy(css = "[data-testid='edit-modal-backdrop']")
    private WebElementFacade modalBackdrop;

    @FindBy(id = "edit-doctor-nombre")
    private WebElementFacade nameField;

    @FindBy(id = "edit-doctor-cedula")
    private WebElementFacade cedulaField;

    @FindBy(id = "edit-doctor-consultorio")
    private WebElementFacade officeSelect;

    @FindBy(id = "edit-doctor-franja")
    private WebElementFacade shiftSelect;

    @FindBy(xpath = "//div[@data-testid='edit-modal-backdrop']//button[normalize-space(text())='Guardar' or normalize-space(text())='Guardando...']")
    private WebElementFacade saveButton;

    @FindBy(xpath = "//div[@data-testid='edit-modal-backdrop']//button[normalize-space(text())='Cerrar']")
    private WebElementFacade closeButton;

    @FindBy(css = "[data-testid='edit-modal-backdrop'] button[aria-label='Cerrar modal']")
    private WebElementFacade closeIconButton;

    public boolean isModalVisible() {
        return modalBackdrop.isVisible();
    }

    public void waitForModal() {
        waitFor(ExpectedConditions.visibilityOf(modalBackdrop.getWrappedElement()));
    }

    public String getNameValue() {
        return nameField.getValue();
    }

    public String getCedulaValue() {
        return cedulaField.getValue();
    }

    public String getOfficeValue() {
        return officeSelect.getSelectedValue();
    }

    public String getShiftValue() {
        return shiftSelect.getSelectedValue();
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

    public void clickOutsideModal() {
        modalBackdrop.click();
    }

    public void pressEscape() {
        modalBackdrop.sendKeys(Keys.ESCAPE);
    }
}
