FROM bellsoft/liberica-openjdk-debian

#install jq and curl
RUN apt-get update && apt-get install -y curl jq dos2unix

#workspcae
WORKDIR /home/selenium-docker

COPY target/docker-resources     ./
COPY runner.sh                   runner.sh

# Fix for windows
RUN dos2unix runner.sh

# Start the runner.sh
ENTRYPOINT sh runner.sh
