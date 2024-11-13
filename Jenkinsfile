pipeline{
    agent any

    stages{
        stage("BUILD JAR***************"){
            steps{
               bat "mvn clean package -DskipTests"
            }
        }

        stage("BUILD IMAGE***************"){

            steps{
                bat "docker build -t=raptor22sq/seleniumautomation ."
            }

        }
        stage("PUSH IMAGE*************"){

            steps{
                bat "docker push raptor22sq/seleniumautomation:latest"
                bat "docker tag raptor22sq/seleniumautomation:latest raptor22sq/seleniumautomation:${env.BUILD_NUMBER}"
                bat "docker push raptor22sq/seleniumautomation:${env.BUILD_NUMBER}"
            }
        }
    }

    post {
        always {
            bat "docker logout"
        }
    }
}


