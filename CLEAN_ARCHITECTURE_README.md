# Estructura Clean Architecture + DDD + CQRS

- domain: entidades, repositorios, lógica de negocio
- application: casos de uso, comandos, queries, handlers
- infrastructure: persistencia, servicios externos
- presentation: controladores, DTOs

## Recomendaciones
- Mantén dependencias mínimas en domain.
- Usa interfaces para repositorios y servicios en domain.
- Implementa CQRS en application (Command, Query, Handler).
- Infraestructura implementa interfaces de domain.
- Presentation solo expone endpoints y mapea DTOs.

## Próximos pasos
1. Crear ejemplos base de cada capa.
2. Agregar dependencias útiles (ej: MapStruct, Lombok, Spring Data, Mediator).
3. Configurar pruebas y calidad de código.
