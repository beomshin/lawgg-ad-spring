FROM openjdk:8-alpine

WORKDIR /

ARG JAR_FILE_PATH=/target/lawgg-admin-0.0.1-SNAPSHOT.jar

ENV ACTIVE_PROFILE="local"

COPY /${JAR_FILE_PATH} ROOT.jar

EXPOSE 18001

ENTRYPOINT ["java", "-jar", "-Xincgc", "-Xmx128m", "-Dspring.profiles.active=${ACTIVE_PROFILE}", "-Duser.timezone=Asia/Seoul", "-Dcom.sun.management.jmxremote", "-Dcom.sun.management.jmxremote.port=37020", "-Dcom.sun.management.jmxremote.rmi.port=37020", "-Djava.rmi.server.hostname=lawgg-ad-spring", "-Dcom.sun.management.jmxremote.ssl=false", "-Dcom.sun.management.jmxremote.authenticate=false", "ROOT.jar"]

