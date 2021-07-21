# ------------------------------------------------------------------------
#
# Directorio de ubicacion de todos los microservicio /opt/<NOMBRE>
#
# docker build -f Dockerfile -t ${artifactId} .
# 
# ------------------------------------------------------------------------

FROM openjdk:11-jre-slim-buster
COPY ./src/${artifactId}.jar /opt/microservice/${artifactId}.jar
ENTRYPOINT ["java","-jar","/opt/microservice/${artifactId}.jar"]