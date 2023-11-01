FROM amazoncorretto:17 AS builder
WORKDIR /app
COPY . ./
RUN ./gradlew bootJar -i --stacktrace

FROM amazoncorretto:17-alpine-jdk
WORKDIR /app
COPY --from=builder /app/build/libs/*.jar ./app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]

