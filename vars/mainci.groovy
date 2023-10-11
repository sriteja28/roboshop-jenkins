def call() {
    node('workstation') {

        if (env.cibuild == "java") {
            stage('Build') {
                sh 'mvn package'
            }
        }

        stage('Unit tests') {
            echo 'Unit tests'
        }

        stage('Code Analysis') {
            echo 'sonar'
            // sh 'sonar-scanner -Dsonar.host.url=http://172.31.81.241:9000 -Dsonar.login=admin -Dsonar.password=admin123 -Dsonar.projectKey=frontend -Dsonar.qualitygate.wait=true'
        }

        stage('Security Scans') {
            echo 'Security Scans'
        }

        stage('Publish a Artifact') {
            echo 'Publish a Artifact'
        }

    }

}
