FROM williamyeh/java8
LABEL version=1.0.0 maintainer="docker user devonmusa2015@gmail.com"

VOLUME  /tmp


COPY mw-netty-1.0.0-SNAPSHOT.jar  /User/mw-netty-1.0.0-SNAPSHOT.jar



#WORKDIR
EXPOSE  18081
CMD     ["javac"]
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/User/mw-netty-1.0.0-SNAPSHOT.jar"]

