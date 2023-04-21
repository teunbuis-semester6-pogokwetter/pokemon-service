FROM openjdk:17-jdk-alpine
COPY build/libs/*.jar ./
ENTRYPOINT ["java","-jar","app.jar"]