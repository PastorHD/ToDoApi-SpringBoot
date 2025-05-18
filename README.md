# ToDoApi-SpringBoot

Documentación del Proyecto: API REST ToDo con Spring Boot
Descripción
Este proyecto consiste en una API REST desarrollada en Spring Boot para la gestión de tareas ToDo, con persistencia en una base de datos MySQL. La API permite crear, obtener, actualizar (completo o solo estatus) y eliminar tareas, aplicando buenas prácticas de desarrollo, validaciones y manejo adecuado de respuestas HTTP.

Estructura del Proyecto
controller: Contiene los controladores REST que exponen los endpoints para el manejo de tareas.

service: Implementa la lógica de negocio, separando la capa de servicio de la capa de acceso a datos.

repository: Contiene las interfaces que extienden JpaRepository para el acceso a la base de datos.

entity: Define las entidades JPA que modelan las tablas en la base de datos.

exception: Incluye el manejo global de excepciones para validar entradas y controlar errores.

| Método | Ruta                      | Descripción                                | Request Body                         | Código HTTP esperado           |
| ------ | ------------------------- | ------------------------------------------ | ------------------------------------ | ------------------------------ |
| POST   | `/api/todos`              | Crea una nueva tarea ToDo                  | JSON con descripción, fecha, estatus | 201 Created                    |
| GET    | `/api/todos`              | Obtiene todas las tareas registradas       | N/A                                  | 200 OK                         |
| PUT    | `/api/todos/{id}`         | Actualiza toda la información de una tarea | JSON con campos actualizados         | 200 OK o 404 Not Found         |
| PATCH  | `/api/todos/{id}/estatus` | Actualiza solo el estatus de una tarea     | JSON con nuevo estatus               | 200 OK o 404 Not Found         |
| DELETE | `/api/todos/{id}`         | Elimina una tarea por ID                   | N/A                                  | 204 No Content o 404 Not Found |

---

## Validaciones Implementadas

- La descripción no puede estar vacía.  
- La fecha debe ser una fecha válida y presente o pasada.  
- El estatus es obligatorio y no puede estar vacío.

En caso de datos inválidos, la API devuelve un mensaje de error claro con código HTTP 400.

---
## Manejo de Errores

- Respuestas con códigos HTTP adecuados según la operación y resultado.  
- Manejo global de excepciones para validar las entradas y controlar recursos no encontrados (404).  
- Mensajes de error estructurados en formato JSON para facilitar el consumo por clientes.

## Instrucciones para Ejecutar el Proyecto Localmente
### Requisitos
  - Java 17 o superior
  - Maven 3 o superior
  - MySQL (o XAMPP con MySQL instalado)
  - IDE recomendado: IntelliJ IDEA, Eclipse o VSCode

### Pasos

1. Clonar el repositorio:

   ```bash
   git clone https://github.com/pastor_hd/todo-api-springboot.git
   cd todo-api-springboot
   ```
2. Crear la base de datos MySQL. (Ex: todo_bd)
3 Configurar la conexión a la base de datos en src/main/resources/application.properties:
 ```bash
  spring.datasource.url=jdbc:mysql://localhost:3306/[DataBase Name]?useSSL=false&serverTimezone=UTC
  spring.datasource.username=[usser]
  spring.datasource.password=[password]
 ```
4. Construir y ejecutar la aplicación:
```bash
   mvn clean install
   mvn spring-boot:run
```
6. La API estará disponible en:
  http://localhost:8080/api/todos
