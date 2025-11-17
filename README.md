# Especificación de Requisitos
- Java Development Kit (JDK) 21+
- Windows, Linux o macOS

# Arquitectura de la Solución

El sistema implementa una simulación de un sistema de archivos de solo lectura basado en SquashFS. La arquitectura sigue un diseño modular con separación de responsabilidades:

- **Capa de Presentación**: `Main.java` - Punto de entrada de la aplicación
- **Capa de Lógica**: `Simulador.java` - Gestión de operaciones del sistema de archivos
- **Capa de Sistema de Archivos**: `SquashFS.java` - Implementación del sistema de archivos de solo lectura
- **Capa de Datos**: `File.java` - Representación de archivos y directorios

## Flujo de Datos
1. El usuario usa el simulador a través de `Main.java`
2. `Simulador.java` procesa las operaciones y se comunica con `SquashFS.java`
3. `SquashFS.java` gestiona la estructura de archivos usando instancias de `File.java`
4. Las operaciones de lectura son procesadas y los resultados devueltos al usuario

# Descripción de Módulos

## Main.java
Clase principal que inicializa el simulador y proporciona la interfaz de usuario para interactuar con el sistema de archivos.

## Simulador.java
Controlador principal que coordina las operaciones del sistema de archivos, incluyendo navegación, lectura y listado de archivos.

## SquashFS.java
Implementación del sistema de archivos de solo lectura. Gestiona la estructura jerárquica de archivos y directorios, asegurando que no se permitan operaciones de escritura.

## File.java
Modelo de datos que representa archivos y directorios. Contiene atributos como ruta, contenido, tamaño, e inodo.

# Manejo de Excepciones

El sistema implementa un manejo de excepciones basico con base en if y else, estos en caso de que una condicion no se cumpla, muestra un print con el error correspondiente.


# Guía de Ejecución

## Compilación y Ejecución
El programa se puede ejecutar desde la línea de comandos. Primero, compile los archivos Java usando el siguiente comando:
Nos posicionamos fuera de la carpeta src y ejecutamos:

```bash
javac -d bin *.java
java -cp bin Main

