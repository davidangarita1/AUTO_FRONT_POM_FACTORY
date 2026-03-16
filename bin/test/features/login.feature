Feature: Inicio de sesion

  @crear_usuario
  Scenario: El usuario inicia sesion con credenciales validas
    Given el usuario se encuentra en la pagina de inicio de sesion
    When ingresa el email "usuario_test@correo.com" y la contrasena "Test1234!"
    And hace clic en el boton de iniciar sesion
    Then el sistema redirige al dashboard
    And el navbar muestra la opcion de cerrar sesion
