# ------------------------------------------------------------------------
#
# Directorio de ubicacion de todos los microservicio /opt/<NOMBRE>
#
# docker build -f Dockerfile -t telefonica.com.co .
# 
# ------------------------------------------------------------------------

FROM openjdk:8-jdk-alpine
MAINTAINER coearquitectura@telefonica.com

VOLUME /tmp
ADD telefonica.com.co.jar /opt/microservice/
EXPOSE 8080
WORKDIR /opt/microservice/
CMD ["java", "-Djava.security.egd=file:/dev/./urandom", "-Xms512m", "-Xmx1g", "-jar", "telefonica.com.co.jar"]
#ENTRYPOINT [ "sh", "-c", "java -jar $JAVA_OPTS /opt/mobile-app-ws/telefonica.com.co.jar" ]