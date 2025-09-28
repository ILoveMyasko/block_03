FROM gradle:8.5-jdk21-alpine AS builder

WORKDIR /home/gradle/project

COPY --chown=gradle:gradle . .

RUN gradle build --no-daemon

FROM tomcat:11.0.11-jdk21-temurin

RUN rm -rf /usr/local/tomcat/webapps/*

COPY --from=builder /home/gradle/project/build/libs/*.war /usr/local/tomcat/webapps/app.war

EXPOSE 8080