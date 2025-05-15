# Start with Maven to build
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN  --mount=type=cache,target=/root/.m2/repository mvn package -DskipTests

# Now run with a lightweight JRE
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/target/consent-api-DEV-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
