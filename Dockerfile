FROM openjdk:20-jdk
WORKDIR /app
CMD ["ls"]
COPY ./target/copinx-0.0.1-SNAPSHOT.jar /app/copinx.jar
COPY src/main/resources /app/src/main/resources
EXPOSE 8080
CMD ["java", "-jar", "copinx.jar"]