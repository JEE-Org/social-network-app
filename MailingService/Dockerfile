#build
FROM openjdk:17-alpine as BUILD

COPY ./.mvn ./.mvn
COPY ./mvnw ./mvnw
COPY ./pom.xml ./pom.xml


# Grant executable permissions to mvnw script
RUN chmod +x ./mvnw   

RUN ./mvnw dependency:go-offline -B
COPY ./src ./src

RUN ./mvnw package spring-boot:repackage -DskipTests

FROM openjdk:17-alpine

COPY --from=BUILD target/*.jar app.jar

EXPOSE 8094

ENTRYPOINT ["java","-jar","/app.jar"]