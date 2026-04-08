# AUTO_FRONT_POM_FACTORY

Proyecto de automatizacion de pruebas UI para el Sistema de Turnos, construido con **Java 21 + Serenity BDD 5.3.2 + Cucumber 7.34.2 + Gradle** usando el patron **Page Object Model (POM) con Page Factory (@FindBy)**. Cubre los modulos de **Inicio de Sesion** y **Gestion de Medicos**.

## Prerrequisitos

1. **Java 21** o superior instalado y configurado en el PATH
2. **Google Chrome** instalado (el driver se descarga automaticamente via Serenity)
3. **La aplicacion bajo prueba** corriendo en `http://localhost:3001` (frontend Next.js)
4. **La API backend** corriendo en `http://localhost:3000` (necesaria para la creacion del usuario de prueba y la limpieza de datos)

## Estructura del proyecto

```
AUTO_FRONT_POM_FACTORY/
├── build.gradle                    ← Dependencias y plugins (Serenity, Cucumber, JUnit 5)
├── settings.gradle
├── serenity.properties             ← Nombre del proyecto para reportes
├── gradlew / gradlew.bat
├── gradle/wrapper/
└── src/
    └── test/
        ├── java/
        │   └── com/turnos/automation/
        │       ├── pages/              ← Page Objects con @FindBy
        │       │   ├── SignInPage.java              ← Formulario de inicio de sesion
        │       │   ├── DashboardPage.java           ← Verificacion de carga del dashboard
        │       │   ├── NavbarComponent.java         ← Barra de navegacion (enlaces, cerrar sesion)
        │       │   ├── DoctorsManagementPage.java   ← Tabla de medicos, botones de accion
        │       │   ├── DoctorFormModalComponent.java ← Modal de creacion de medicos
        │       │   ├── DoctorEditModalComponent.java ← Modal de edicion de medicos
        │       │   ├── ConfirmDeleteModalComponent.java ← Modal de confirmacion de baja
        │       │   └── ToastComponent.java          ← Mensajes flotantes (exito/error)
        │       ├── hooks/              ← Hooks de Cucumber
        │       │   ├── UserSetupHook.java           ← @crear_usuario: crea usuario via API
        │       │   └── DoctorCleanupHook.java       ← @limpiar_medicos: limpieza pre/post test
        │       ├── stepdefinitions/    ← Step Definitions de Cucumber
        │       │   ├── LoginStepDefinitions.java
        │       │   └── DoctorsManagementStepDefinitions.java
        │       ├── util/               ← Constantes del proyecto
        │       │   └── Constants.java
        │       └── runners/            ← Runner de Cucumber con Serenity
        │           └── CucumberTestRunner.java
        └── resources/
            ├── serenity.conf           ← Configuracion de WebDriver y Serenity
            └── features/               ← Archivos Gherkin
                ├── login.feature
                └── doctors_management.feature
```

## Arquitectura y patrones

### Page Object Model (POM) con Page Factory

Cada pagina o componente de la UI tiene su propia clase Java que encapsula los localizadores (`@FindBy`) y las interacciones. Esto separa la logica de los tests de la estructura del DOM.

| Componente | Clase | Responsabilidad |
|---|---|---|
| Pagina de login | `SignInPage` | Campos email/password, boton submit, mensaje de error |
| Dashboard | `DashboardPage` | Verificacion de URL post-login |
| Barra de navegacion | `NavbarComponent` | Links de navegacion, boton cerrar sesion |
| Pantalla de medicos | `DoctorsManagementPage` | Tabla, encabezados, botones editar/eliminar, esperas |
| Modal de creacion | `DoctorFormModalComponent` | Campos del formulario, validaciones, selects con React |
| Modal de edicion | `DoctorEditModalComponent` | Precarga de datos, cambio de consultorio/franja |
| Modal de confirmacion | `ConfirmDeleteModalComponent` | Mensaje de confirmacion, botones Cancelar/Aceptar |
| Mensajes flotantes | `ToastComponent` | Captura de toasts de exito y error via `role="alert"` |

### Tags de Cucumber

| Tag | Proposito | Hook asociado |
|---|---|---|
| `@crear_usuario` | Crea el usuario de prueba via API antes del escenario | `UserSetupHook.java` |
| `@limpiar_medicos` | Limpia todos los medicos via API antes y despues del escenario | `DoctorCleanupHook.java` |

### Estrategia de datos de prueba

- Los **numeros de cedula** se generan dinamicamente con `System.currentTimeMillis()` para evitar colisiones con registros de ejecuciones anteriores (la eliminacion en el sistema es logica, no fisica).
- La **limpieza de medicos** se realiza via API REST (`DELETE /api/v1/doctors/:id`) autenticandose con el usuario de prueba, tanto antes como despues de cada escenario etiquetado con `@limpiar_medicos`.
- Los **selects de React** se manejan con `JavascriptExecutor` para disparar el evento `change` correctamente en componentes controlados.

## Escenarios cubiertos

### Inicio de sesion (login.feature)

| # | Escenario | Tipo |
|---|-----------|------|
| 1 | El usuario inicia sesion con credenciales validas | Positivo (Scenario Outline) |
| 2 | El sistema rechaza el inicio de sesion con credenciales invalidas | Negativo (Scenario Outline) |

### Gestion de Medicos (doctors_management.feature)

| # | Escenario | HU | Tipo |
|---|-----------|----| -----|
| 3 | La barra de navegacion muestra el enlace Gestion Medicos | HU-01 | Positivo |
| 4 | La pantalla muestra la estructura correcta (titulo, encabezados, boton) | HU-01 | Positivo |
| 5 | Crear un medico con consultorio y franja horaria | HU-02 | Positivo |
| 6 | Crear un medico sin consultorio ni franja horaria | HU-02 | Positivo |
| 7 | El nombre vacio impide guardar | HU-02 | Negativo |
| 8 | El nombre con menos de 3 caracteres impide guardar | HU-02 | Negativo |
| 9 | La cedula rechaza letras y solo acepta numeros | HU-02 | Negativo |
| 10 | Consultorio seleccionado sin franja horaria impide guardar | HU-02 | Negativo |
| 11 | Editar consultorio y franja de un medico | HU-03 | Positivo |
| 12 | Clic fuera del modal de edicion no lo cierra | HU-03 | Negativo |
| 13 | Confirmar la baja logica de un medico | HU-04 | Positivo |
| 14 | Cancelar la baja cierra el modal sin cambios | HU-04 | Negativo |

### Cobertura por Historia de Usuario

| HU | Descripcion | Escenarios |
|---|---|---|
| HU-01 | Acceso y visualizacion del modulo | 2 |
| HU-02 | Crear un nuevo medico | 6 |
| HU-03 | Editar un medico creado | 2 |
| HU-04 | Dar de baja a un medico | 2 |

## Ejecucion de las pruebas

```bash
./gradlew clean test
```

Para ejecutar solo una feature especifica:

```bash
./gradlew clean test -Dcucumber.filter.tags="@crear_usuario"
```

## Generacion del reporte Serenity

```bash
./gradlew aggregate
```

## Ver el reporte

Abrir en el navegador:

```
target/site/serenity/index.html
```

Tambien se genera un resumen en una sola pagina:

```
target/site/serenity/serenity-summary.html
```

## Configuracion

La configuracion del navegador y del entorno se encuentra en `src/test/resources/serenity.conf`:

| Parametro | Valor | Descripcion |
|---|---|---|
| `webdriver.driver` | `chrome` | Navegador utilizado |
| `webdriver.autodownload` | `true` | Descarga automatica del driver |
| `headless.mode` | `false` | Ejecucion con ventana visible |
| `serenity.browser.width` | `1920` | Ancho de la ventana |
| `serenity.browser.height` | `1080` | Alto de la ventana |
| `serenity.take.screenshots` | `AFTER_EACH_STEP` | Captura despues de cada paso |
| `webdriver.base.url` | `http://localhost:3001` | URL del frontend |
| `api.base.url` | `http://localhost:3000` | URL del backend API |

## Stack tecnologico

| Tecnologia | Version | Proposito |
|---|---|---|
| Java | 21 | Lenguaje principal |
| Serenity BDD | 5.3.2 | Framework de testing y reportes |
| Cucumber | 7.34.2 | BDD con Gherkin |
| JUnit 5 | 5.13.0 | Plataforma de ejecucion |
| AssertJ | 3.23.1 | Aserciones fluidas |
| Gradle | Wrapper | Build tool |
| Selenium WebDriver | 4.x | Automatizacion del navegador |

