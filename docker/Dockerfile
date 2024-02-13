FROM crayonman/java17-alpine-ocr as onsquad

COPY ./build/libs/OnSquadServer-0.0.1-SNAPSHOT.jar /project/
WORKDIR /project
RUN chmod +x /project/OnSquadServer-0.0.1-SNAPSHOT.jar
EXPOSE 8080
CMD ["java", "-jar", "OnSquadServer-0.0.1-SNAPSHOT.jar"]
