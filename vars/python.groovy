def call() {
    pipeline {

        agent {
            node { label 'workstation' }
        }

        stages {

            stage('Unit tests') {
                steps {
                    echo 'Unit tests'
                    //sh 'python -m unittest'
                }
            }

            stage('Code Analysis') {
                steps {
                    echo 'sonar'
                    // sh 'sonar-scanner -Dsonar.host.url=http://172.31.81.241:9000 -Dsonar.login=admin -Dsonar.password=admin123 -Dsonar.projectKey=payment'
                }
            }

            stage('Security Scans') {
                steps {
                    echo 'Security Scans'
                }
            }

            stage('Publish a Artifact') {
                steps {
                    echo 'Publish a Artifact'
                }
            }

        }

    }


}