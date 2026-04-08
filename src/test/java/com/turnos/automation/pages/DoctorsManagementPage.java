package com.turnos.automation.pages;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.annotations.DefaultUrl;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

@DefaultUrl("/doctors")
public class DoctorsManagementPage extends PageObject {

    @FindBy(css = "h1")
    private WebElementFacade pageTitle;

    @FindBy(css = "table thead th")
    private List<WebElementFacade> tableHeaders;

    @FindBy(xpath = "//button[normalize-space(text())='Crear médico']")
    private WebElementFacade createDoctorButton;

    @FindBy(css = "table tbody tr")
    private List<WebElementFacade> tableRows;

    @FindBy(xpath = "//td[contains(text(),'No hay médicos creados')]")
    private WebElementFacade emptyTableMessage;

    public String getPageTitle() {
        waitFor(ExpectedConditions.urlContains("/doctors"));
        pageTitle.waitUntilVisible();
        return pageTitle.getText();
    }

    public List<String> getTableHeaders() {
        return tableHeaders.stream()
                .map(WebElementFacade::getText)
                .collect(Collectors.toList());
    }

    public boolean isCreateButtonVisible() {
        return createDoctorButton.isVisible();
    }

    public void clickCreateDoctor() {
        createDoctorButton.click();
    }

    public boolean isEmptyMessageVisible() {
        return emptyTableMessage.isVisible();
    }

    public boolean isDoctorInTable(String doctorName) {
        return getDriver().findElements(By.xpath(
                "//table//tbody//tr//td[normalize-space(.)='" + doctorName + "']"))
                .size() > 0;
    }

    public String getDoctorOffice(String doctorName) {
        return getDriver().findElement(By.xpath(
                "//table//tbody//tr[td[normalize-space(.)='" + doctorName + "']]/td[3]"))
                .getText();
    }

    public String getDoctorShift(String doctorName) {
        return getDriver().findElement(By.xpath(
                "//table//tbody//tr[td[normalize-space(.)='" + doctorName + "']]/td[4]"))
                .getText();
    }

    public void clickEditDoctor(String doctorDisplayName) {
        getDriver().findElement(By.cssSelector(
                "button[aria-label='Editar " + doctorDisplayName + "']")).click();
    }

    public void clickDeleteDoctor(String doctorDisplayName) {
        getDriver().findElement(By.cssSelector(
                "button[aria-label='Dar de baja " + doctorDisplayName + "']")).click();
    }

    public void waitForTableToLoad() {
        waitFor(ExpectedConditions.presenceOfElementLocated(By.cssSelector("table tbody")));
    }

    public void waitForDoctorInTable(String doctorName) {
        withTimeoutOf(Duration.ofSeconds(15)).waitFor(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//table//tbody//tr//td[normalize-space(.)='" + doctorName + "']")));
    }

    public void waitForDoctorNotInTable(String doctorName) {
        withTimeoutOf(Duration.ofSeconds(15)).waitFor(ExpectedConditions.invisibilityOfElementLocated(
                By.xpath("//table//tbody//tr//td[normalize-space(.)='" + doctorName + "']")));
    }
}
