#基础镜像指定
FROM williamyeh/java8
LABEL version=1.0.0 maintainer="docker user devonmusa2015@gmail.com"

#
VOLUME  /tmp
#ADD spring-boot-docker-1.0.jar app.jar


# copy  src  target
COPY mw-netty-1.0.0-SNAPSHOT.jar  /User/mw-netty-1.0.0-SNAPSHOT.jar


#WORKDIR

#RUN <command> 或 RUN ["", "", ""]
#RUN     /bin/echo -e "LANG=\"en_US.UTF-8\"" >/Users/default/local
EXPOSE  18081

#CMD ["","",""]
CMD     ["javac"]
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/User/mw-netty-1.0.0-SNAPSHOT.jar"]

