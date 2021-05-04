FROM debian:bullseye

ARG USER_ID
ARG GROUP_ID

RUN apt-get update -y && \
	apt-get install -y curl gnupg2 procps default-jre default-jdk scala

RUN curl -sL 'https://downloads.apache.org/spark/spark-3.1.1/spark-3.1.1-bin-hadoop2.7.tgz' | tar --extract --gzip && \
	mv 'spark-3.1.1-bin-hadoop2.7' /opt/spark

RUN groupadd docker --gid "$GROUP_ID" && addgroup --uid "$GROUP_ID" spark
RUN adduser --disabled-password --gecos '' --uid "$USER_ID" --gid "$GROUP_ID" spark

USER spark

WORKDIR /src

CMD bash
