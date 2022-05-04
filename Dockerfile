FROM maven:latest as builder
WORKDIR /build
COPY . .
RUN mvn clean package -P dev -DskipTests

FROM openjdk:11
WORKDIR /app
COPY --from=builder ./build/target/*.jar ./application.jar
EXPOSE 8080

ENTRYPOINT java -jar application.jar