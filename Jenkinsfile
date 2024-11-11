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
                bat "docker build -t=raptor22sq/seleniumjenk ."
            }

        }
        stage("PUSH IMAGE*************"){

            steps{
                bat "docker push raptor22sq/seleniumjenk:latest"
                bat "docker tag raptor22sq/seleniumjenk:latest raptor22sq/seleniumjenk:${env.BUILD_NUMBER}"
                bat "docker push raptor22sq/seleniumjenk:${env.BUILD_NUMBER}"
            }
        }
    }

    post {
        always {
            bat "docker logout"
        }
    }
}


