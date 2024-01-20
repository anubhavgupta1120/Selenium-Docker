FROM bellsoft/liberica-openjdk-alpine:17.0.8
RUN apk add curl
RUN apk add jq
WORKDIR /home/Selenium-Docker
ADD target/docker-resources ./
ADD runner.sh runner.sh
#HUB_HOST
#BROWSER
#TEST_SUITE
#THREAD_COUNT
ENTRYPOINT sh runner.sh