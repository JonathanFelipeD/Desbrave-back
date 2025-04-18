FROM maven:3.9.8-amazoncorretto-21 AS build
WORKDIR /app
COPY . .

RUN mvn clean package -DskipTests -Dmaven.resources.encoding=UTF-8

FROM openjdk:21-jdk
WORKDIR /app

EXPOSE 8080

COPY --from=build /app/target/Desbrave-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]