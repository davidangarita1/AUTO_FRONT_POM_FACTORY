Feature: Inicio de sesion

  @crear_usuario
  Scenario: El usuario inicia sesion con credenciales validas
    Given el usuario se encuentra en la pagina de inicio de sesion
    When ingresa las credenciales validas
    And hace clic en el boton de iniciar sesion
    Then el sistema redirige al dashboard
    And el navbar muestra la opcion de cerrar sesion

  @crear_usuario
  Scenario: El sistema rechaza el inicio de sesion con credenciales invalidas
    Given el usuario se encuentra en la pagina de inicio de sesion
    When ingresa una contrasena incorrecta
    And hace clic en el boton de iniciar sesion
    Then el sistema muestra un mensaje de error de "Invalid credentials"
