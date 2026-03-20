# AUTO_FRONT_POM_FACTORY

Proyecto de automatización de pruebas UI para el Sistema de Turnos, construido con **Java + Serenity BDD + Cucumber + Gradle** usando el patrón **Page Object Model (POM) con Page Factory (@FindBy)**.

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
        │       │   └── NavbarComponent.java
        │       ├── hooks/              ← Hooks de Cucumber
        │       │   └── UserSetupHook.java
        │       ├── stepdefinitions/    ← Step Definitions de Cucumber
        │       │   └── LoginStepDefinitions.java
        │       ├── util/               ← Constantes del proyecto
        │       │   └── Constants.java
        │       └── runners/            ← Runner de Cucumber con Serenity
        │           └── CucumberTestRunner.java
        └── resources/
            ├── serenity.conf           ← Configuración de WebDriver y Serenity
            └── features/               ← Archivos Gherkin
                └── login.feature
```

## Escenarios cubiertos

| # | Feature | Escenario | Tipo |
|---|---------|-----------|------|
| 1 | Inicio de sesion | El usuario inicia sesion con credenciales validas | Positivo (Scenario Outline) |
| 2 | Inicio de sesion | El sistema rechaza el inicio de sesion con credenciales invalidas | Negativo (Scenario Outline) |

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

