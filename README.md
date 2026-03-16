# AUTO_FRONT_POM_FACTORY

Proyecto de automatización de pruebas UI para el Sistema de Turnos, construido con **Java + Serenity BDD + Cucumber + Gradle** usando el patrón **Page Object Model (POM) con Page Factory (@FindBy)**.

## Prerrequisitos

1. **Java 17** o superior instalado y configurado en el PATH
2. **Google Chrome** instalado (el driver se descarga automáticamente vía Serenity)
3. **La aplicación bajo prueba** corriendo en `http://localhost:3001`
   - Sistema de Turnos (Next.js)
   - Rutas disponibles: `/signin`, `/signup`, `/dashboard`

## Estructura del proyecto

```
AUTO_FRONT_POM_FACTORY/
├── build.gradle
├── settings.gradle
├── gradlew
├── gradlew.bat
├── gradle/wrapper/
└── src/
    └── test/
        ├── java/
        │   └── com/turnos/automation/
        │       ├── pages/          ← Page Objects con @FindBy
        │       │   ├── SignInPage.java
        │       │   ├── SignUpPage.java
        │       │   ├── DashboardPage.java
        │       │   └── NavbarComponent.java
        │       ├── steps/          ← Step Definitions de Cucumber
        │       │   ├── LoginStepDefinitions.java
        │       │   └── SignupStepDefinitions.java
        │       └── runners/        ← Runner de Cucumber con Serenity
        │           └── TestRunner.java
        └── resources/
            ├── serenity.conf       ← Configuración de WebDriver y Serenity
            ├── junit-platform.properties
            └── features/           ← Archivos Gherkin
                ├── login.feature
                └── signup.feature
```

## Escenarios cubiertos

| # | Feature | Escenario | Tipo |
|---|---------|-----------|------|
| 1 | Inicio de sesion | Inicio de sesión exitoso con credenciales válidas | Positivo |
| 2 | Registro de usuario | Rechazo de registro con contraseña débil | Negativo |

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

- Navegador: Chrome en modo headless
- URL base: `http://localhost:3001`
- Resolución: 1920×1080
- Screenshots: después de cada paso

