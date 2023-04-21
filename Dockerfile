FROM openjdk:17-jdk-alpine
RUN mkdir/app
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} /app/pokemonservice.jar
ENTRYPOINT ["java","-jar","/app/pokemonservice.jar"]