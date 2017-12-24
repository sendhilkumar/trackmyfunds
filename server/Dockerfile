FROM openjdk:8-jdk

COPY /config.yml /tmp/trackmyfunds/config.yml
COPY /server-1.0-SNAPSHOT.jar /tmp/trackmyfunds/server-1.0-SNAPSHOT.jar

WORKDIR /tmp/trackmyfunds

RUN java -version

CMD ["java","-jar","server-1.0-SNAPSHOT.jar","server","config.yml"]

EXPOSE 8080