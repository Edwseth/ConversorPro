# ConversorPro

ConversorPro es una aplicación de consola en Java que permite realizar conversiones de monedas y medidas, así como cálculos matemáticos básicos, con la opción de registrar el historial de operaciones.

## Estructura del Proyecto

ConversorPro/
│
├── src/
│   ├── com/
│   │   ├── conversorpro/
│   │   │   ├── Conversores/
│   │   │   │   ├── Conversor.java
│   │   │   │   ├── ConversorMonedas.java
│   │   │   │   ├── ConversorMedidas.java
│   │   │   │   ├── TasaCambioRespuesta.java
│   │   │   ├── Historial/
│   │   │   │   ├── HistorialGeneral.java
│   │   │   │   ├── HistorialMoneda.java
│   ├── Calculadora.java
│   ├── Main.java

### Paquetes principales
- **conversores**: Contiene las clases para realizar conversiones de monedas y medidas.
- **historial**: Contiene las clases para gestionar y almacenar el historial de operaciones.
- **calculadora**: Contiene la calculadora básica con historial.

## Características
- **Conversión de monedas**: Realiza conversiones de moneda en tiempo real usando una API externa.
- **Conversión de medidas**: Convierte entre diferentes unidades de longitud (metros, kilómetros, pies, pulgadas).
- **Calculadora básica**: Realiza operaciones de suma, resta, multiplicación y división con historial de operaciones.
- **Historial**: Almacena un historial detallado de todas las conversiones y cálculos realizados.

## Instalación
1. Clona el repositorio:
   ```bash
   git clone https://github.com/Edwseth/ConversorPro.git
2. Compila el proyecto usando tu IDE favorito o mediante la terminal:
   ```bash
   javac -d bin src/com/conversorpro/Main.java
3. Ejecuta el programa:
   ```bash
   java -cp bin com.conversorpro.Main

## Uso
Al ejecutar la aplicación, se te presentará un menú donde puedes seleccionar entre las siguientes opciones:

1. Uso de la calculadora.
2. Conversión de monedas.
3. Conversión de medidas.
4. Ver el historial de operaciones.

Selecciona la opción deseada ingresando el número correspondiente.

## Requisitos
- **Java 8 o superior**
- **Conexión a Internet para las conversiones de moneda**
## Contribuciones
Las contribuciones son bienvenidas. Por favor, sigue estos pasos:

1. Haz un fork del proyecto.
2. Crea una nueva rama (git checkout -b feature/nueva-caracteristica).
3. Realiza tus cambios y haz un commit (git commit -am 'Agrega nueva característica').
4. Haz un push a la rama (git push origin feature/nueva-caracteristica).
5. Crea un Pull Request.
## Licencia
Este proyecto está bajo la Licencia MIT - consulta el archivo LICENSE para más detalles.

### Notas adicionales:
- Asegúrate de reemplazar la URL del repositorio en la sección de instalación si es diferente.
- Si tienes otros archivos adicionales (como una licencia), asegúrate de incluirlos en la raíz del proyecto.

Este archivo `README.md` está diseñado para que sea claro y profesional, proporcionando a los usuarios toda la información necesaria para entender, instalar y usar tu aplicación. ¿Hay algún otro aspecto que quieras añadir o modificar?
