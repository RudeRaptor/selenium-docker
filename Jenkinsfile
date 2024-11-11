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
                bat "docker build -t=raptor22sq/seleniumJenk ."
            }

        }
        stage("PUSH IMAGE*************"){

            steps{
                bat "docker push raptor22sq/seleniumjenk"
            }
        }
    }

    post {
        always {
            bat "docker logout"
        }
    }
}


