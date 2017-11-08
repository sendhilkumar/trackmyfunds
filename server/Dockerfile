FROM openjdk:8-jdk

COPY /src/main/resources/config.yml /data/trackmyfunds/config.yml
COPY /target/server-1.0-SNAPSHOT.jar /data/trackmyfunds/server-1.0-SNAPSHOT.jar

WORKDIR /data/trackmyfunds

RUN java -version

CMD ["java","-jar","server-1.0-SNAPSHOT.jar","server","config.yml"]

EXPOSE 8080-8081  