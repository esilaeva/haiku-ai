FROM openjdk:17-alpine

USER root

RUN mkdir -p /home/spring_ai_test

WORKDIR /home/spring_ai_test

COPY samples-ai-0.0.1-SNAPSHOT.jar /home/spring_ai_test/spring-ai.jar

ENTRYPOINT ["java", "-jar", "spring-ai.jar"]


