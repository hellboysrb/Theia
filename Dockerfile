FROM openjdk:10-jdk-slim
WORKDIR /app
COPY target/smigic-0.0.1-SNAPSHOT.jar .
CMD ["java","-jar", "-Djava.security.egd=file:/dev/./urandom", "-Dspring.config.location=classpath:/,/app/", "/app/smigic-0.0.1-SNAPSHOT.jar”]