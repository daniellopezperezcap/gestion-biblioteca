# Gestión Biblioteca

Este proyecto es una aplicación de gestión de biblioteca desarrollada con Spring Boot. Permite gestionar libros, préstamos y usuarios.

## Requisitos

- Java 11 o superior
- Maven 3.6.3 o superior

## Configuración de la base de datos

La aplicación utiliza una base de datos en memoria H2. La configuración de la base de datos se encuentra en el archivo `application.properties`:

```properties
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password cd gestion-biblioteca
Compila y ejecuta la aplicación con Maven:

mvn clean install
mvn spring-boot:run
Accede a la consola de H2:

URL: http://localhost:8080/h2-console
JDBC URL: jdbc:h2:mem:testdb
User Name: sa
Password: (deja este campo vacío)
Accede a la documentación de la API con Swagger:

URL: http://localhost:8080/swagger-ui/index.html#/
Descripción del funcionamiento
La aplicación permite realizar las siguientes operaciones:

Libros:

Listar todos los libros
Obtener un libro por ID
Crear un nuevo libro
Actualizar un libro existente
Eliminar un libro por ID
Préstamos:

Listar todos los préstamos
Obtener un préstamo por ID
Crear un nuevo préstamo
Actualizar un préstamo existente
Eliminar un préstamo por ID
Usuarios:

Listar todos los usuarios
Obtener un usuario por ID
Crear un nuevo usuario
Actualizar un usuario existente
Eliminar un usuario por ID
Contribuciones
Las contribuciones son bienvenidas. Por favor, abre un issue o envía un pull request para discutir cualquier cambio que desees realizar.

Licencia
Este proyecto está licenciado bajo la Licencia MIT. Consulta el archivo LICENSE para obtener más detalles.