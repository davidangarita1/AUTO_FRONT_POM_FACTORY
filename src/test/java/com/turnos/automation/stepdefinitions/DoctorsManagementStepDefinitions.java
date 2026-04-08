package com.turnos.automation.stepdefinitions;

import com.turnos.automation.pages.ConfirmDeleteModalComponent;
import com.turnos.automation.pages.DoctorEditModalComponent;
import com.turnos.automation.pages.DoctorFormModalComponent;
import com.turnos.automation.pages.DoctorsManagementPage;
import com.turnos.automation.pages.NavbarComponent;
import com.turnos.automation.pages.ToastComponent;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class DoctorsManagementStepDefinitions {

    private NavbarComponent navbarComponent;
    private DoctorsManagementPage doctorsPage;
    private DoctorFormModalComponent createModal;
    private DoctorEditModalComponent editModal;
    private ConfirmDeleteModalComponent deleteModal;
    private ToastComponent toastComponent;

    private String lastCreatedCedula;

    private String uniqueCedula(String base) {
        lastCreatedCedula = String.valueOf(System.currentTimeMillis() % 10000000L);
        return lastCreatedCedula;
    }

    @Then("la barra de navegacion muestra el enlace {string}")
    public void navbarShowsLink(String linkText) {
        assertThat(navbarComponent.isLinkVisible(linkText))
                .as("El enlace '" + linkText + "' debe ser visible en la barra de navegacion")
                .isTrue();
    }

    @When("el usuario hace clic en el enlace {string}")
    public void userClicksLink(String linkText) {
        navbarComponent.clickLink(linkText);
    }

    @Then("la pantalla muestra el titulo {string}")
    public void pageShowsTitle(String expectedTitle) {
        assertThat(doctorsPage.getPageTitle())
                .as("El titulo de la pantalla debe ser: " + expectedTitle)
                .isEqualTo(expectedTitle);
    }

    @And("la tabla muestra los encabezados {string}")
    public void tableShowsHeaders(String headersCommaSeparated) {
        List<String> expectedHeaders = Arrays.asList(headersCommaSeparated.split(","));
        List<String> currentHeaders = doctorsPage.getTableHeaders();
        assertThat(currentHeaders)
                .as("Los encabezados de la tabla deben coincidir (case-insensitive)")
                .usingElementComparator(String.CASE_INSENSITIVE_ORDER)
                .containsExactlyElementsOf(expectedHeaders);
    }

    @And("el boton {string} es visible")
    public void buttonIsVisible(String buttonText) {
        assertThat(doctorsPage.isCreateButtonVisible())
                .as("El boton '" + buttonText + "' debe ser visible")
                .isTrue();
    }

    @And("hace clic en el boton {string}")
    public void clickButton(String buttonText) {
        if ("Crear médico".equals(buttonText)) {
            doctorsPage.clickCreateDoctor();
        }
    }

    @Then("se abre el modal de creacion con los campos esperados")
    public void createModalOpensWithExpectedFields() {
        createModal.waitForModal();
        assertThat(createModal.isNameFieldVisible()).as("Campo nombre visible").isTrue();
        assertThat(createModal.isCedulaFieldVisible()).as("Campo cedula visible").isTrue();
        assertThat(createModal.isOfficeSelectVisible()).as("Campo consultorio visible").isTrue();
        assertThat(createModal.isShiftSelectVisible()).as("Campo franja visible").isTrue();
    }

    @When("ingresa el nombre {string} y la cedula {string}")
    public void enterNameAndCedula(String name, String cedula) {
        createModal.waitForModal();
        createModal.enterName(name);
        createModal.enterCedula(uniqueCedula(cedula));
    }

    @And("selecciona el consultorio {string} y la franja horaria {string}")
    public void selectOfficeAndShift(String office, String shift) {
        createModal.selectOffice(office);
        createModal.selectShift(shift);
    }

    @And("hace clic en el boton {string} del modal")
    public void clickModalButton(String buttonText) {
        if ("Guardar".equals(buttonText)) {
            createModal.clickSave();
        } else if ("Cerrar".equals(buttonText)) {
            createModal.clickClose();
        }
    }

    @Then("aparece el mensaje flotante {string}")
    public void toastMessageAppears(String expectedMessage) {
        String actualMessage = toastComponent.getToastMessage();
        assertThat(actualMessage)
                .as("El mensaje flotante debe contener: " + expectedMessage)
                .contains(expectedMessage);
    }

    @And("la tabla muestra al medico {string} con consultorio {string} y franja {string}")
    public void tableShowsDoctor(String doctorName, String office, String shift) {
        doctorsPage.waitForDoctorInTable(doctorName);
        assertThat(doctorsPage.isDoctorInTable(doctorName))
                .as("El medico '" + doctorName + "' debe estar en la tabla")
                .isTrue();
        assertThat(doctorsPage.getDoctorOffice(doctorName))
                .as("El consultorio debe ser: " + office)
                .isEqualTo(office);
        assertThat(doctorsPage.getDoctorShift(doctorName))
                .as("La franja horaria debe ser: " + shift)
                .isEqualTo(shift);
    }

    @When("el usuario toca el campo nombre y sale sin escribir")
    public void touchNameFieldAndLeave() {
        createModal.waitForModal();
        createModal.touchNameFieldAndLeave();
    }

    @When("el usuario escribe {string} en el campo nombre")
    public void writeInNameField(String text) {
        createModal.waitForModal();
        createModal.enterName(text);
        createModal.touchCedulaFieldAndLeave();
    }

    @When("el usuario escribe {string} en el campo cedula")
    public void writeInCedulaField(String text) {
        createModal.waitForModal();
        createModal.enterCedula(text);
    }

    @Then("aparece el mensaje de validacion {string}")
    public void validationMessageAppears(String expectedMessage) {
        assertThat(createModal.hasValidationMessage(expectedMessage))
                .as("Debe aparecer el mensaje de validacion: " + expectedMessage)
                .isTrue();
    }

    @And("el boton {string} del modal esta deshabilitado")
    public void modalButtonIsDisabled(String buttonText) {
        assertThat(createModal.isSaveButtonEnabled())
                .as("El boton '" + buttonText + "' debe estar deshabilitado")
                .isFalse();
    }

    @Then("el campo cedula muestra solo {string}")
    public void cedulaFieldShowsOnly(String expectedValue) {
        assertThat(createModal.getCedulaValue())
                .as("El campo cedula debe mostrar solo: " + expectedValue)
                .isEqualTo(expectedValue);
    }

    @And("selecciona el consultorio {string} sin seleccionar franja horaria")
    public void selectOfficeWithoutShift(String office) {
        createModal.selectOffice(office);
    }

    @And("crea un medico {string} con cedula {string} consultorio {string} y franja {string}")
    public void createDoctorWithDetails(String name, String cedula, String office, String shift) {
        doctorsPage.clickCreateDoctor();
        createModal.waitForModal();
        createModal.enterName(name);
        createModal.enterCedula(uniqueCedula(cedula));
        createModal.selectOffice(office);
        createModal.selectShift(shift);
        createModal.clickSave();
        doctorsPage.waitForDoctorInTable("Dr. " + name);
    }

    @And("hace clic en el icono de editar del medico {string}")
    public void clickEditDoctorIcon(String doctorDisplayName) {
        doctorsPage.clickEditDoctor(doctorDisplayName);
    }

    @Then("se abre el modal de edicion con los datos del medico")
    public void editModalOpensWithDoctorData() {
        editModal.waitForModal();
        assertThat(editModal.isModalVisible())
                .as("El modal de edicion debe estar visible")
                .isTrue();
    }

    @When("cambia el consultorio a {string} y la franja horaria a {string}")
    public void changeOfficeAndShift(String office, String shift) {
        editModal.selectOffice(office);
        editModal.selectShift(shift);
    }

    @And("hace clic en el boton {string} del modal de edicion")
    public void clickEditModalButton(String buttonText) {
        if ("Guardar".equals(buttonText)) {
            editModal.clickSave();
        } else if ("Cerrar".equals(buttonText)) {
            editModal.clickClose();
        }
    }

    @When("el usuario hace clic fuera del modal de edicion")
    public void clickOutsideEditModal() {
        editModal.clickOutsideModal();
    }

    @Then("el modal de edicion permanece abierto")
    public void editModalRemainsOpen() {
        assertThat(editModal.isModalVisible())
                .as("El modal de edicion debe permanecer abierto")
                .isTrue();
    }

    @And("hace clic en el icono de dar de baja del medico {string}")
    public void clickDeleteDoctorIcon(String doctorDisplayName) {
        doctorsPage.clickDeleteDoctor(doctorDisplayName);
    }

    @Then("aparece el modal de confirmacion con el mensaje del medico {string}")
    public void confirmationModalAppearsWithMessage(String doctorName) {
        deleteModal.waitForModal();
        String message = deleteModal.getConfirmationMessage();
        assertThat(message)
                .as("El mensaje debe mencionar al Dr. " + doctorName)
                .contains("Dr. " + doctorName);
    }

    @When("el usuario hace clic en el boton {string} del modal de confirmacion")
    public void clickConfirmationModalButton(String buttonText) {
        if ("Aceptar".equals(buttonText)) {
            deleteModal.clickConfirm();
        } else if ("Cancelar".equals(buttonText)) {
            deleteModal.clickCancel();
        }
    }

    @And("el medico {string} no aparece en la tabla")
    public void doctorNotInTable(String doctorName) {
        doctorsPage.waitForDoctorNotInTable(doctorName);
        assertThat(doctorsPage.isDoctorInTable(doctorName))
                .as("El medico '" + doctorName + "' no debe aparecer en la tabla")
                .isFalse();
    }

    @Then("el medico {string} aparece en la tabla")
    public void doctorInTable(String doctorName) {
        assertThat(doctorsPage.isDoctorInTable(doctorName))
                .as("El medico '" + doctorName + "' debe aparecer en la tabla")
                .isTrue();
    }

    @And("el usuario toca el campo cedula y sale sin escribir")
    public void touchCedulaFieldAndLeave() {
        createModal.waitForModal();
        createModal.touchCedulaFieldAndLeave();
    }

    @When("ingresa el nombre {string} y la cedula duplicada del medico creado")
    public void enterNameAndDuplicateCedula(String name) {
        createModal.waitForModal();
        createModal.enterName(name);
        createModal.enterCedula(lastCreatedCedula);
    }

    @And("da de baja al medico {string}")
    public void deactivateDoctor(String doctorDisplayName) {
        doctorsPage.clickDeleteDoctor(doctorDisplayName);
        deleteModal.waitForModal();
        deleteModal.clickConfirm();
        doctorsPage.waitForDoctorNotInTable(doctorDisplayName);
    }

    @When("ingresa el nombre {string} y la cedula reutilizada del medico dado de baja")
    public void enterNameAndReusedCedula(String name) {
        createModal.waitForModal();
        createModal.enterName(name);
        createModal.enterCedula(lastCreatedCedula);
    }

    @When("el usuario presiona la tecla Escape en el modal de edicion")
    public void pressEscapeOnEditModal() {
        editModal.pressEscape();
    }
}
