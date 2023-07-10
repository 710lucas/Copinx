FROM openjdk:20-jdk
WORKDIR /app
<<<<<<< HEAD
COPY ./target/copinx-0.0.1-SNAPSHOT.jar /app/copinx.jar
=======
COPY target/copinx-0.0.1-SNAPSHOT.jar /app/copinx.jar
>>>>>>> origin/dev
EXPOSE 8080
CMD ["java", "-jar", "copinx.jar"]