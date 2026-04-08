Feature: Gestion de Medicos

  Background:
    Given el usuario se encuentra en la pagina de inicio de sesion
    When ingresa el correo "usuario_test@correo.com" y la contrasena "Test1234!"
    And hace clic en el boton de iniciar sesion
    Then el sistema redirige al dashboard

  @crear_usuario
  Scenario: La barra de navegacion muestra el enlace Gestion Medicos para usuario autenticado
    Then la barra de navegacion muestra el enlace "Gestión Médicos"

  @crear_usuario
  Scenario: La pantalla de gestion de medicos muestra la estructura correcta
    When el usuario hace clic en el enlace "Gestión Médicos"
    Then la pantalla muestra el titulo "Gestión de Médicos"
    And la tabla muestra los encabezados "Nombre completo,Cédula,Consultorio,Franja Horaria,Acciones"
    And el boton "Crear médico" es visible

  @crear_usuario @limpiar_medicos
  Scenario Outline: Crear un medico con consultorio y franja horaria
    When el usuario hace clic en el enlace "Gestión Médicos"
    And hace clic en el boton "Crear médico"
    Then se abre el modal de creacion con los campos esperados
    When ingresa el nombre "<nombre>" y la cedula "<cedula>"
    And selecciona el consultorio "<consultorio>" y la franja horaria "<franja>"
    And hace clic en el boton "Guardar" del modal
    Then aparece el mensaje flotante "Médico creado exitosamente"
    And la tabla muestra al medico "Dr. <nombre>" con consultorio "<consultorio>" y franja "<franja>"

    Examples:
      | nombre        | cedula  | consultorio | franja      |
      | Carlos Prueba | 7654321 | 8           | 06:00-14:00 |
      | Luis Vargas   | 2345678 | 2           | 14:00-22:00 |

  @crear_usuario @limpiar_medicos
  Scenario Outline: Crear un medico sin consultorio ni franja horaria
    When el usuario hace clic en el enlace "Gestión Médicos"
    And hace clic en el boton "Crear médico"
    When ingresa el nombre "<nombre>" y la cedula "<cedula>"
    And hace clic en el boton "Guardar" del modal
    Then aparece el mensaje flotante "Médico creado exitosamente"
    And la tabla muestra al medico "Dr. <nombre>" con consultorio "Sin asignar" y franja "Sin asignar"

    Examples:
      | nombre        | cedula  |
      | Ana Martinez  | 8765432 |

  @crear_usuario
  Scenario: El nombre vacio impide guardar
    When el usuario hace clic en el enlace "Gestión Médicos"
    And hace clic en el boton "Crear médico"
    When el usuario toca el campo nombre y sale sin escribir
    Then aparece el mensaje de validacion "El nombre completo es obligatorio"
    And el boton "Guardar" del modal esta deshabilitado

  @crear_usuario
  Scenario: El nombre con menos de 3 caracteres impide guardar
    When el usuario hace clic en el enlace "Gestión Médicos"
    And hace clic en el boton "Crear médico"
    When el usuario escribe "Ju" en el campo nombre
    Then aparece el mensaje de validacion "El nombre debe tener mínimo 3 caracteres"
    And el boton "Guardar" del modal esta deshabilitado

  @crear_usuario
  Scenario Outline: La cedula filtra caracteres no numericos
    When el usuario hace clic en el enlace "Gestión Médicos"
    And hace clic en el boton "Crear médico"
    When el usuario escribe "<entrada>" en el campo cedula
    Then el campo cedula muestra solo "<resultado>"

    Examples:
      | entrada | resultado |
      | ABC123  | 123       |
      | 456XYZ  | 456       |

  @crear_usuario
  Scenario: Consultorio seleccionado sin franja horaria impide guardar
    When el usuario hace clic en el enlace "Gestión Médicos"
    And hace clic en el boton "Crear médico"
    When ingresa el nombre "Juan Garcia" y la cedula "1234567"
    And selecciona el consultorio "2" sin seleccionar franja horaria
    Then aparece el mensaje de validacion "La franja horaria es obligatoria cuando se asigna un consultorio"
    And el boton "Guardar" del modal esta deshabilitado

  @crear_usuario @limpiar_medicos
  Scenario Outline: Editar consultorio y franja de un medico
    When el usuario hace clic en el enlace "Gestión Médicos"
    And crea un medico "<nombre>" con cedula "<cedula>" consultorio "<consultorio_inicial>" y franja "<franja_inicial>"
    And hace clic en el icono de editar del medico "Dr. <nombre>"
    Then se abre el modal de edicion con los datos del medico
    When cambia el consultorio a "<consultorio_nuevo>" y la franja horaria a "<franja_nueva>"
    And hace clic en el boton "Guardar" del modal de edicion
    Then aparece el mensaje flotante "Médico guardado exitosamente"
    And la tabla muestra al medico "Dr. <nombre>" con consultorio "<consultorio_nuevo>" y franja "<franja_nueva>"

    Examples:
      | nombre      | cedula  | consultorio_inicial | franja_inicial | consultorio_nuevo | franja_nueva |
      | Pedro Lopez | 9876543 | 3                   | 06:00-14:00    | 4                 | 14:00-22:00  |
      | Ana Castro  | 1234560 | 1                   | 14:00-22:00    | 2                 | 06:00-14:00  |

  @crear_usuario @limpiar_medicos
  Scenario: Clic fuera del modal de edicion no lo cierra
    When el usuario hace clic en el enlace "Gestión Médicos"
    And crea un medico "Laura Gomez" con cedula "5432198" consultorio "5" y franja "14:00-22:00"
    And hace clic en el icono de editar del medico "Dr. Laura Gomez"
    When el usuario hace clic fuera del modal de edicion
    Then el modal de edicion permanece abierto

  @crear_usuario @limpiar_medicos
  Scenario: Confirmar la baja logica de un medico
    When el usuario hace clic en el enlace "Gestión Médicos"
    And crea un medico "Maria Ruiz" con cedula "6789012" consultorio "6" y franja "06:00-14:00"
    And hace clic en el icono de dar de baja del medico "Dr. Maria Ruiz"
    Then aparece el modal de confirmacion con el mensaje del medico "Maria Ruiz"
    When el usuario hace clic en el boton "Aceptar" del modal de confirmacion
    Then aparece el mensaje flotante "Médico dado de baja exitosamente"
    And el medico "Dr. Maria Ruiz" no aparece en la tabla

  @crear_usuario @limpiar_medicos
  Scenario: Cancelar la baja cierra el modal sin cambios
    When el usuario hace clic en el enlace "Gestión Médicos"
    And crea un medico "Sofia Torres" con cedula "3456789" consultorio "7" y franja "14:00-22:00"
    And hace clic en el icono de dar de baja del medico "Dr. Sofia Torres"
    Then aparece el modal de confirmacion con el mensaje del medico "Sofia Torres"
    When el usuario hace clic en el boton "Cancelar" del modal de confirmacion
    Then el medico "Dr. Sofia Torres" aparece en la tabla

  @crear_usuario
  Scenario: Cerrar el modal de creacion sin guardar no agrega medicos
    When el usuario hace clic en el enlace "Gestión Médicos"
    And hace clic en el boton "Crear médico"
    Then se abre el modal de creacion con los campos esperados
    When ingresa el nombre "Temporal" y la cedula "9999999"
    And hace clic en el boton "Cerrar" del modal
    Then el medico "Dr. Temporal" no aparece en la tabla

  @crear_usuario
  Scenario: La cedula vacia impide guardar
    When el usuario hace clic en el enlace "Gestión Médicos"
    And hace clic en el boton "Crear médico"
    When el usuario escribe "Juan Garcia" en el campo nombre
    And el usuario toca el campo cedula y sale sin escribir
    Then aparece el mensaje de validacion "El número de cédula es obligatorio"
    And el boton "Guardar" del modal esta deshabilitado

  @crear_usuario @limpiar_medicos
  Scenario: La cedula duplicada en un medico activo muestra alerta
    When el usuario hace clic en el enlace "Gestión Médicos"
    And crea un medico "Original Doctor" con cedula "1111111" consultorio "8" y franja "06:00-14:00"
    And hace clic en el boton "Crear médico"
    When ingresa el nombre "Duplicado Doctor" y la cedula duplicada del medico creado
    And hace clic en el boton "Guardar" del modal
    Then aparece el mensaje flotante "Ya existe un médico registrado con ese número de cédula"

  @crear_usuario @limpiar_medicos
  Scenario: La cedula de un medico dado de baja no permite crear otro medico
    When el usuario hace clic en el enlace "Gestión Médicos"
    And crea un medico "Medico Baja" con cedula "2222222" consultorio "2" y franja "14:00-22:00"
    And da de baja al medico "Dr. Medico Baja"
    And hace clic en el boton "Crear médico"
    When ingresa el nombre "Medico Nuevo" y la cedula reutilizada del medico dado de baja
    And hace clic en el boton "Guardar" del modal
    Then aparece el mensaje flotante "HTTP_ERROR_500"

  @crear_usuario @limpiar_medicos
  Scenario: Cerrar el modal de edicion sin guardar preserva los datos
    When el usuario hace clic en el enlace "Gestión Médicos"
    And crea un medico "Elena Rios" con cedula "4567890" consultorio "3" y franja "06:00-14:00"
    And hace clic en el icono de editar del medico "Dr. Elena Rios"
    Then se abre el modal de edicion con los datos del medico
    When hace clic en el boton "Cerrar" del modal de edicion
    Then la tabla muestra al medico "Dr. Elena Rios" con consultorio "3" y franja "06:00-14:00"

  @crear_usuario @limpiar_medicos
  Scenario: La tecla Escape no cierra el modal de edicion
    When el usuario hace clic en el enlace "Gestión Médicos"
    And crea un medico "Ricardo Mora" con cedula "5678901" consultorio "4" y franja "14:00-22:00"
    And hace clic en el icono de editar del medico "Dr. Ricardo Mora"
    When el usuario presiona la tecla Escape en el modal de edicion
    Then el modal de edicion permanece abierto
