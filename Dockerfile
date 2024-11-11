FROM bellsoft/liberica-openjdk-debian

#install jq and curl
RUN apt-get update && apt-get install -y curl jq

#workspcae
WORKDIR /home/selenium-docker

ADD target/docker-resources     ./
ADD runner.sh                   runner.sh

# Start the runner.sh
ENTRYPOINT sh runner.sh

