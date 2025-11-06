# Despliegue en Render

1. Ve a la sección de Environment en Render y agrega las variables:
   - DB_URL
   - DB_USER
   - DB_PASS
   - JWT_SECRET
2. Usa el perfil de producción en Render:
   - SPRING_PROFILES_ACTIVE=prod
3. No subas tus archivos `application.properties` con datos sensibles al repositorio.
4. Usa `application.properties.example` como referencia para nuevos desarrolladores.

# Logging y manejo de errores
- Configura el nivel de logging en los archivos de perfil.
- Usa controladores globales para manejo de excepciones.
- Revisa la documentación de Spring Boot para logging y seguridad.
