def call() {
    node('workstation') {

        stage('Code Checkout') {
//            sh 'env'
//            sh 'find . | grep "^./" |xargs rm -rf'

            if(env.TAG_NAME ==~ ".*") {
                env.gitbrname = "refs/tags/${env.TAG_NAME}"
            } else {
                env.gitbrname = "${env.BRANCH_NAME}"
            }
            checkout scm: [$class: 'GitSCM', userRemoteConfigs: [[url: "https://github.com/sriteja28/${env.component}"]], branches: [[name: gitbrname]]], poll: false

            // git branch: env.BRANCH_NAME, url: 'https://github.com/sriteja28/frontend'
        }

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

        if (env.TAG_NAME ==~ ".*") {
            stage('Publish a Artifact') {
                if (env.cibuild == "nginx"){
                    sh 'zip -r ${component}-${TAG_NAME}.zip *'
                }
            }
        }

    }
}
