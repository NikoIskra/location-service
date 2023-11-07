FROM amazoncorretto:17.0.8-alpine3.18

ADD app/build/libs/app.jar location-service.jar
ENTRYPOINT [ "java", "-jar", "location-service.jar" ]