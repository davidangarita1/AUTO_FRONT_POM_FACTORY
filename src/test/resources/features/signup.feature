# language: es
Feature: Registro de usuario

  Scenario: El sistema rechaza el registro con una contrasena debil
    Given el usuario se encuentra en la pagina de registro
    When ingresa el nombre "Test User" el email "test_weak@correo.com" y la contrasena "1234"
    And hace clic en el boton de registrarse
    Then el sistema muestra un mensaje de error indicando que la contrasena es debil
    And el usuario permanece en la pagina de registro
