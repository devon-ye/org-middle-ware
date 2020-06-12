#基础镜像指定
FROM openjdk:8-jdk-alpine
LABEL version=1.0.0 maintainer="docker user devonmusa2015@gmail.com"
#
VOLUME  /tmp
#ADD spring-boot-docker-1.0.jar app.jar


ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]



RUN apt-get update

