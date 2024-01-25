# USUAL JRE BUILD (196mb)

# FROM maven:3.9.5-eclipse-temurin-11-alpine AS builder
# WORKDIR /app
# COPY pom.xml .
# RUN mvn dependency:go-offline

# COPY src/ ./src/
# RUN mvn install

# FROM eclipse-temurin:11-jre-alpine
# COPY --from=builder /app/target/api-0.0.1-SNAPSHOT.jar api-0.0.1-SNAPSHOT.jar
# ENTRYPOINT ["java", "-jar", "/api-0.0.1-SNAPSHOT.jar"]





# FROM --platform=$BUILDPLATFORM eclipse-temurin:11-jdk-alpine AS builder
# #ADDING MAVEN TO CONTAINER
# RUN apk add --no-cache bash procps curl tar

# ENV MAVEN_HOME /usr/share/maven

# COPY --from=maven:3.9.3-eclipse-temurin-11 ${MAVEN_HOME} ${MAVEN_HOME}
# COPY --from=maven:3.9.3-eclipse-temurin-11 /usr/local/bin/mvn-entrypoint.sh /usr/local/bin/mvn-entrypoint.sh
# COPY --from=maven:3.9.3-eclipse-temurin-11 /usr/share/maven/ref/settings-docker.xml /usr/share/maven/ref/settings-docker.xml

# RUN ln -s ${MAVEN_HOME}/bin/mvn /usr/bin/mvn

# ARG MAVEN_VERSION=3.9.3
# ARG USER_HOME_DIR="/root"
# ENV MAVEN_CONFIG "$USER_HOME_DIR/.m2"

# ENTRYPOINT ["/usr/local/bin/mvn-entrypoint.sh"]
# CMD ["mvn"]

# #APP BUILDING
# WORKDIR /workdir/server
# COPY pom.xml /workdir/server/pom.xml
# RUN mvn dependency:go-offline

# COPY src /workdir/server/src
# RUN mvn install

# FROM builder as prepare-production
# RUN mkdir -p target/dependency
# WORKDIR /workdir/server/target/dependency
# RUN jar -xf ../*.jar

# FROM eclipse-temurin:11-jre-alpine

# EXPOSE 8080
# VOLUME /tmp
# ARG DEPENDENCY=/workdir/server/target/dependency
# COPY --from=prepare-production ${DEPENDENCY}/BOOT-INF/lib /app/lib
# COPY --from=prepare-production ${DEPENDENCY}/META-INF /app/META-INF
# COPY --from=prepare-production ${DEPENDENCY}/BOOT-INF/classes /app
# #SETTING REVERSE PROXY FOR HTTP CONNECTIONS
# # ENV SYS_PROPS="-Dhttp.proxyHost=localhost.test -Dhttp.proxyPort=80"
# # "-Dhttp.proxyHost=localhost", "-Dhttp.proxyPort=80",
# ENTRYPOINT ["java","-cp","app:app/lib/*","com.neGleb1.api.ApiApplication"]


# THE BEST DOCKERFILE FOR JAVA
# SMALL JRE BUILD (138mb!): https://github.com/monosoul/jvm-in-docker

# base image to build a JRE and app
FROM maven:3.9-eclipse-temurin-17-alpine AS builder

# JAVA APP BUILDING
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline

COPY src/ ./src/
# MAVEN TESTS SKIPPING IS IMPORTANT!!!
# DB CONTAINER IS NOT CREATED YET DURING TESTS EXECUTION
RUN mvn clean package -DskipTests
#RUN mvn clean package

# JRE BUILDING
# required for strip-debug to work
RUN apk add --no-cache binutils
# Build small JRE image
RUN $JAVA_HOME/bin/jlink \
--verbose \
# ADDS ALL JAVA MODULES TO BUILD
--add-modules ALL-MODULE-PATH \
# I CAN ADD ONLY NECESSARY MODULES INSTEAD TO REDUCE IMAGE SIZE
#--add-modules java.base,java.management,java.naming,java.net.http,java.security.jgss,java.security.sasl,java.sql,jdk.httpserver,jdk.unsupported \
--strip-debug \
--no-man-pages \
--no-header-files \
--compress=2 \
--output /customjre

# main app image
FROM alpine:latest
ENV JAVA_HOME=/jre
ENV PATH="${JAVA_HOME}/bin:${PATH}"
# copy JRE from the base image
COPY --from=builder /customjre $JAVA_HOME
# Add app user
# ARG APPLICATION_USER=appuser
# RUN adduser --no-create-home -u 1000 -D $APPLICATION_USER
# Configure working directory
# RUN mkdir /app && \
# chown -R $APPLICATION_USER /app
RUN mkdir /app
# USER 1000
# COPY --chown=1000:1000 ./app.jar /app/app.jar
COPY --from=builder /app/target/api-0.0.1-SNAPSHOT.jar /app/api-0.0.1-SNAPSHOT.jar
WORKDIR /app
EXPOSE 8080
ENTRYPOINT [ "/jre/bin/java", "-jar", "/app/api-0.0.1-SNAPSHOT.jar" ]






# FROM --platform=$BUILDPLATFORM maven:3.9.5-eclipse-temurin-11-alpine AS builder

# #APP BUILDING
# WORKDIR /workdir/server
# # COPY pom.xml /workdir/server/pom.xml
# # THIS COMMAND CACHES ALL DEPENDENCIES FROM pom.xml
# # RUN mvn dependency:go-offline

# COPY pom.xml /workdir/server/pom.xml
# COPY src /workdir/server/src
# RUN mvn install

# # FROM builder as prepare-production
# # RUN mkdir -p target/dependency
# # WORKDIR /workdir/server/target/dependency
# #opens jar and copy files
# # RUN jar -xf ../*.jar

# FROM eclipse-temurin:11-jre-alpine

# EXPOSE 8080
# VOLUME /tmp
# # ARG DEPENDENCY=/workdir/server/target/dependency
# # COPY --from=prepare-production ${DEPENDENCY}/BOOT-INF/lib /app/lib
# # COPY --from=prepare-production ${DEPENDENCY}/META-INF /app/META-INF
# # COPY --from=prepare-production ${DEPENDENCY}/BOOT-INF/classes /app
# #SETTING REVERSE PROXY FOR HTTP CONNECTIONS
# # ENV SYS_PROPS="-Dhttp.proxyHost=localhost.test -Dhttp.proxyPort=80"
# # "-Dhttp.proxyHost=localhost", "-Dhttp.proxyPort=80",
# ENTRYPOINT ["java","-cp","app:app/lib/*","com.neGleb1.api.ApiApplication"]





# # First stage: complete build environment
# FROM maven:3.5.0-jdk-8-alpine AS builder

# # add pom.xml and source code
# ADD ./pom.xml pom.xml
# ADD ./src src/

# # package jar
# RUN mvn clean package

# # Second stage: minimal runtime environment
# From openjdk:8-jre-alpine

# # copy jar from the first stage
# COPY --from=builder target/my-app-1.0-SNAPSHOT.jar my-app-1.0-SNAPSHOT.jar

# EXPOSE 8080

# CMD ["java", "-jar", "my-app-1.0-SNAPSHOT.jar"]




# FROM --platform=$BUILDPLATFORM maven:3.8.5-eclipse-temurin-17 AS build
# WORKDIR /workdir/server
# COPY pom.xml /workdir/server/pom.xml
# RUN mvn dependency:go-offline

# COPY src /workdir/server/src

# RUN mvn --batch-mode clean compile assembly:single

# FROM build AS dev-envs
# RUN <<EOF
# apt-get update
# apt-get install -y --no-install-recommends git
# EOF

# RUN <<EOF
# useradd -s /bin/bash -m vscode
# groupadd docker
# usermod -aG docker vscode
# EOF
# # install Docker tools (cli, buildx, compose)
# COPY --from=gloursdocker/docker / /
# CMD ["java", "-jar", "target/app.jar" ]

# FROM eclipse-temurin:17-jre-focal
# ARG DEPENDENCY=/workdir/server/target
# EXPOSE 8080
# COPY --from=build ${DEPENDENCY}/app.jar /app.jar
# CMD java -jar /app.jar