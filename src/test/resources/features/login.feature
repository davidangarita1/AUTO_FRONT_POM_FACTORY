Feature: Inicio de sesion

  @crear_usuario
  Scenario Outline: El usuario inicia sesion con credenciales validas
    Given el usuario se encuentra en la pagina de inicio de sesion
    When ingresa el correo "<email>" y la contrasena "<password>"
    And hace clic en el boton de iniciar sesion
    Then el sistema redirige al dashboard
    And el navbar muestra la opcion de cerrar sesion

    Examples:
      | email                   | password  |
      | usuario_test@correo.com | Test1234! |

  @crear_usuario
  Scenario Outline: El sistema rechaza el inicio de sesion con credenciales invalidas
    Given el usuario se encuentra en la pagina de inicio de sesion
    When ingresa el correo "<email>" y la contrasena "<password>"
    And hace clic en el boton de iniciar sesion
    Then el sistema muestra un mensaje de error de "<mensaje_error>"

    Examples:
      | email                   | password     | mensaje_error       |
      | usuario_test@correo.com | WrongPass99! | Invalid credentials |
