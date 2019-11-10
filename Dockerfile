FROM maven:3-jdk-8

RUN mkdir -p /usr/src/app

WORKDIR /usr/src/app

ADD . .

RUN mvn clean install

RUN mvn compile

EXPOSE 8080 

CMD ["mvn", "spring-boot:run"]


