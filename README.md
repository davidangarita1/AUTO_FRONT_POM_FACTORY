# AUTO_FRONT_POM_FACTORY

Proyecto de automatización de pruebas UI para el Sistema de Turnos, construido con **Java + Serenity BDD + Cucumber + Gradle** usando el patrón **Page Object Model (POM) con Page Factory (@FindBy)**. Cubre los módulos de **Inicio de Sesión** y **Gestión de Médicos**.

## Prerrequisitos

1. **Java 21** o superior instalado y configurado en el PATH
2. **Google Chrome** instalado (el driver se descarga automáticamente vía Serenity)
3. **La aplicación bajo prueba** corriendo en `http://localhost:3001` (frontend Next.js)
4. **La API backend** corriendo en `http://localhost:3000` (necesaria para la creación del usuario de prueba)

## Estructura del proyecto

```
AUTO_FRONT_POM_FACTORY/
├── build.gradle
├── settings.gradle
├── serenity.properties
├── gradlew / gradlew.bat
├── gradle/wrapper/
└── src/
    └── test/
        ├── java/
        │   └── com/turnos/automation/
        │       ├── pages/              ← Page Objects con @FindBy
        │       │   ├── SignInPage.java
        │       │   ├── DashboardPage.java
        │       │   ├── NavbarComponent.java
        │       │   ├── DoctorsManagementPage.java
        │       │   ├── DoctorFormModalComponent.java
        │       │   ├── DoctorEditModalComponent.java
        │       │   ├── ConfirmDeleteModalComponent.java
        │       │   └── ToastComponent.java
        │       ├── hooks/              ← Hooks de Cucumber
        │       │   ├── UserSetupHook.java
        │       │   └── DoctorCleanupHook.java
        │       ├── stepdefinitions/    ← Step Definitions de Cucumber
        │       │   ├── LoginStepDefinitions.java
        │       │   └── DoctorsManagementStepDefinitions.java
        │       ├── util/               ← Constantes del proyecto
        │       │   └── Constants.java
        │       └── runners/            ← Runner de Cucumber con Serenity
        │           └── CucumberTestRunner.java
        └── resources/
            ├── serenity.conf           ← Configuración de WebDriver y Serenity
            └── features/               ← Archivos Gherkin
                ├── login.feature
                └── doctors_management.feature
```

## Escenarios cubiertos

### Inicio de sesion

| # | Feature | Escenario | Tipo |
|---|---------|-----------|------|
| 1 | Inicio de sesion | El usuario inicia sesion con credenciales validas | Positivo (Scenario Outline) |
| 2 | Inicio de sesion | El sistema rechaza el inicio de sesion con credenciales invalidas | Negativo (Scenario Outline) |

### Gestion de Medicos

| # | Feature | Escenario | Tipo | HU |
|---|---------|-----------|------|----|
| 3 | Gestion de Medicos | La barra de navegacion muestra el enlace Gestion Medicos | Positivo | HU-01 |
| 4 | Gestion de Medicos | La pantalla muestra la estructura correcta | Positivo | HU-01 |
| 5 | Gestion de Medicos | Crear un medico con consultorio y franja horaria | Positivo (Scenario Outline) | HU-02 |
| 6 | Gestion de Medicos | Crear un medico sin consultorio ni franja horaria | Positivo (Scenario Outline) | HU-02 |
| 7 | Gestion de Medicos | El nombre vacio impide guardar | Negativo | HU-02 |
| 8 | Gestion de Medicos | El nombre con menos de 3 caracteres impide guardar | Negativo | HU-02 |
| 9 | Gestion de Medicos | La cedula rechaza letras y solo acepta numeros | Negativo | HU-02 |
| 10 | Gestion de Medicos | Consultorio sin franja horaria impide guardar | Negativo | HU-02 |
| 11 | Gestion de Medicos | Editar consultorio y franja de un medico | Positivo | HU-03 |
| 12 | Gestion de Medicos | Clic fuera del modal de edicion no lo cierra | Negativo | HU-03 |
| 13 | Gestion de Medicos | Confirmar la baja logica de un medico | Positivo | HU-04 |
| 14 | Gestion de Medicos | Cancelar la baja cierra el modal sin cambios | Negativo | HU-04 |

## Ejecución de las pruebas

```bash
./gradlew clean test
```

## Generación del reporte Serenity

```bash
./gradlew aggregate
```

## Ver el reporte

Abrir en el navegador:

```
target/site/serenity/index.html
```

## Configuración

La configuración del navegador y del entorno se encuentra en `src/test/resources/serenity.conf`:

- Navegador: Chrome (sin modo headless)
- URL base: `http://localhost:3001`
- Resolución: 1920×1080
- Screenshots: después de cada paso

