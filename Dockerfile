FROM openjdk:8u171-alpine3.7
RUN apk --no-cache add curl
COPY target/hello-world*.jar hello-world.jar
CMD java ${JAVA_OPTS} -jar hello-world.jar