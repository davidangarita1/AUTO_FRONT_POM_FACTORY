package com.turnos.automation.pages;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.annotations.DefaultUrl;
import org.openqa.selenium.support.FindBy;

@DefaultUrl("/signin")
public class SignInPage extends PageObject {

    @FindBy(css = "input[type='email'][placeholder='Email']")
    private WebElementFacade emailField;

    @FindBy(css = "input[type='password'][placeholder='Contraseña']")
    private WebElementFacade passwordField;

    @FindBy(css = "button[type='submit']")
    private WebElementFacade signInButton;

    @FindBy(css = "[role='alert']")
    private WebElementFacade errorMessage;

    public void enterEmail(String email) {
        emailField.clear();
        emailField.sendKeys(email);
    }

    public void enterPassword(String password) {
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public void clickSignIn() {
        signInButton.click();
    }

    public String getErrorMessage() {
        return errorMessage.getText();
    }

    public boolean isErrorMessageVisible() {
        return errorMessage.isVisible();
    }
}
