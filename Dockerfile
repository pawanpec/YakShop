# Ubuntu 15.04 with Java 7 installed.
# Build image with:  docker build -t krizsan/ubuntu1504java7:v1 .

FROM ubuntu:15.04

RUN apt-get update 
RUN apt-get upgrade -y 
RUN apt-get install -y  software-properties-common 
RUN add-apt-repository ppa:webupd8team/java -y 
RUN apt-get update 
RUN echo oracle-java7-installer shared/accepted-oracle-license-v1-1 select true | /usr/bin/debconf-set-selections 
RUN apt-get install -y oracle-java7-installer 
RUN apt-get clean
RUN apt-get install maven
RUN apt-get clean
