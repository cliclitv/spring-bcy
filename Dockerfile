FROM openjdk:17-jdk-alpine
COPY target/bcy-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8888
RUN ls
ENTRYPOINT ["java","-jar","app.jar"]