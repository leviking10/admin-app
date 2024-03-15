FROM openjdk:17-jdk

WORKDIR /app
COPY target/admin-app.jar /app/admin-app.jar
EXPOSE 8089
# Exécuter l'application
CMD ["java", "-jar", "/app/admin-app.jar"]
