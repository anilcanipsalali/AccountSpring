FROM openjdk:11 AS build

COPY pom.xml mvnw ./
COPY .mvn .mvn
RUN ./mvnw dependency:resolve

COPY src src
RUN ./mvnw package

FROM openjdk:11
WORKDIR AccountSpring
COPY --from=build target/*.jar AccountSpring.jar
ENTRYPOINT ["java", "-jar", "AccountSpring.jar"]