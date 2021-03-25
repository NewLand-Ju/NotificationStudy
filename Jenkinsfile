pipeline {
  agent {
    node {
      label 'linux_docker'
    }

  }
  stages {
    stage('Build') {
      steps {
        sh './gradlew clean build'
      }
    }

  }
}