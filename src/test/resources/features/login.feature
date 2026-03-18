Feature: Inicio de sesion

  @crear_usuario
  Scenario: El usuario inicia sesion con credenciales validas
    Given el usuario se encuentra en la pagina de inicio de sesion
    When ingresa el email "usuario_test@correo.com" y la contrasena "Test1234!"
    And hace clic en el boton de iniciar sesion
    Then el sistema redirige al dashboard
    And el navbar muestra la opcion de cerrar sesion

  Scenario: El sistema rechaza el inicio de sesion con credenciales invalidas
    Given el usuario se encuentra en la pagina de inicio de sesion
    When ingresa el email "correo_invalido@test.com" y la contrasena "WrongPass99!"
    And hace clic en el boton de iniciar sesion
    Then el sistema muestra un mensaje de error de credenciales invalidas
