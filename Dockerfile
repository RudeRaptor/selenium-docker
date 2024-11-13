FROM bellsoft/liberica-openjdk-debian

#install jq and curl
RUN apt-get update && apt-get install -y curl jq

#workspcae
WORKDIR /home/selenium-docker

COPY target/docker-resources     ./
COPY runner.sh                   runner.sh

# Make runner.sh executable
RUN chmod +x runner.sh

# Start runner.sh
ENTRYPOINT ["sh", "-c", "exec ./runner.sh"]
