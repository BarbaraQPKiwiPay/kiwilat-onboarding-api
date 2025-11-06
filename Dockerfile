FROM maven:3.9-eclipse-temurin-21-alpine

WORKDIR /app

# Copiar todos los archivos del proyecto
COPY . .

# Construir la aplicación
RUN mvn clean package -DskipTests

# Exponer el puerto
EXPOSE 8080

# Ejecutar la aplicación con el perfil prod
CMD ["java", "-jar", "-Dspring.profiles.active=prod", "target/kiwipay-onboarding-api-0.0.1-SNAPSHOT.jar"]
