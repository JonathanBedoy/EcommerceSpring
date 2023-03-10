# Add another stage to this: one that will build the jar
FROM maven:3-openjdk-17 AS build
COPY src /home/app/src
COPY pom.xml /home/app/
RUN mvn -f /home/app/pom.xml clean package




########################################
# I want this Dockerfile to run my app

# If I want to run a Java app, what is the minimum that I need??? -> A JRE

FROM openjdk:17-ea-3-jdk-slim
# we want to grab the .jar file so it is in the container
COPY --from=build /home/app/target/*.jar app.jar
# expose the correct port
EXPOSE 8080
# as the entrypoint, RUN IT
ENTRYPOINT ["java", "-jar", "app.jar"]