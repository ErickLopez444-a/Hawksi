FROM zhuzhu/gradle-jdk8-alpine
COPY . .
RUN gradle build
EXPOSE 8080
ENTRYPOINT ["java","-jar","build/libs/homebanking-0.0.1-SNAPSHOT.jar"]