FROM ubuntu:latest
LABEL authors="Myasko"

ENTRYPOINT ["top", "-b"]