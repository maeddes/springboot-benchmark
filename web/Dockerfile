FROM openjdk:8-alpine
RUN mkdir -p /opt/SimpleWeb
WORKDIR /opt/SimpleWeb
COPY target/web-0.0.1-SNAPSHOT.jar /opt/SimpleWeb
CMD ["java", "-Xmx64m", "-jar", "web-0.0.1-SNAPSHOT.jar"]
