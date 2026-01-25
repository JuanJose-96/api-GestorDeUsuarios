# API REST para gestión de usuarios con un CRUD básico. 
El proyecto está actualmente en desarrollo: hoy utiliza H2 como base de datos embebida para acelerar el ciclo de implementación y pruebas, y más adelante se migrará a una base de datos relacional PostgreSQL.\
Las pruebas funcionales se realizan con Postman. \
Se añadirá documentación con Swagger/OpenAPI y autenticación mediante JWT
## Tecnologías y enfoque
API REST
- Base de datos H2 (entorno de desarrollo)
- Migración planificada a PostgreSQL
- Postman para pruebas de endpoints
- Swagger/OpenAPI para documentación (a implementar)
- JWT para autenticación/autorización (a implementar)
- DTOs para desacoplar el modelo interno de la capa de transporte
- ResponseEntity para control explícito de códigos de estado y respuestas HTTP

## Características principales:
- CRUD de usuarios:
  - Crear usuario
  - Consultar usuario(s)
  - Actualizar usuario
  - Eliminar usuario

- Respuestas HTTP controladas usando ResponseEntity:
  - 200 OK, 201 Created, 204 No Content
  - 400 Bad Request, 404 Not Found, etc.
- Uso de DTO:
  - Evita exponer directamente entidades para mayor seguridad
  - Facilita validaciones y evolución del contrato API
