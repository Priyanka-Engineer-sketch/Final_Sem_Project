FROM eclipse-temurin:17-jre
WORKDIR /app
COPY target/config-server-*.jar app.jar
EXPOSE 8888
ENTRYPOINT ["java","-XX:+UseContainerSupport","-jar","/app/app.jar"]