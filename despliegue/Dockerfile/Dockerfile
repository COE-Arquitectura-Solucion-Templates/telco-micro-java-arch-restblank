# ------------------------------------------------------------------------
#
# Directorio de ubicacion de todos los microservicio /opt/<NOMBRE>
#
# docker build -f Dockerfile -t msAplyRechargeAsync .
# 
# ------------------------------------------------------------------------

FROM openjdk:11-jre-slim-buster
COPY ./src/msAplyRechargeAsync-0.0.1-SNAPSHOT.jar /opt/microservice/msAplyRechargeAsync-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/opt/microservice/msAplyRechargeAsync-0.0.1-SNAPSHOT.jar"]