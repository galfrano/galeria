pipeline {
   agent any
   stages {
      stage('publish') {
         steps {
            echo 'publishing'
            withCredentials([[$class: 'UsernamePasswordMultiBinding', credentialsId: 'token', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD']]) {
              sh 'npm config set //registry.npmjs.org/:_authToken ${PASSWORD}'
              sh 'npm publish'
            }
         }
      }
   }
}
