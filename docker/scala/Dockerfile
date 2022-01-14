FROM debian:bullseye

ARG USER_ID
ARG GROUP_ID

ENV LANG C.UTF-8
ENV LC_ALL C.UTF-8

RUN apt-get update -y && \
	apt-get install -y sudo curl gnupg2 default-jre default-jdk scala

RUN echo "deb https://repo.scala-sbt.org/scalasbt/debian all main" | sudo tee /etc/apt/sources.list.d/sbt.list && \
    echo "deb https://repo.scala-sbt.org/scalasbt/debian /" | sudo tee /etc/apt/sources.list.d/sbt_old.list && \
    curl -sL "https://keyserver.ubuntu.com/pks/lookup?op=get&search=0x2EE0EA64E40A89B84B2DF73499E82A75642AC823" | sudo apt-key add && \
    apt-get update -y && \
    apt-get install -y sbt

RUN groupadd docker --gid "$GROUP_ID" && addgroup --uid "$GROUP_ID" scala
RUN adduser --disabled-password --gecos '' --uid "$USER_ID" --gid "$GROUP_ID" scala
USER scala

RUN mkdir ~/.sbt ~/.cache

WORKDIR /src
