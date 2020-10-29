FROM java:8

EXPOSE 8881

ADD target/quarkusmicro-1.0.0-SNAPSHOT-runner.jar quarkusmicro-1.0.0-SNAPSHOT-runner.jar
ENTRYPOINT ["java","-jar","quarkusmicro-1.0.0-SNAPSHOT-runner.jar"]
