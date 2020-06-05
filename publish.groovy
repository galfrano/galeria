pipeline {
   agent any
   stages {
      stage('test') {
         steps {
            echo 'publishing'
            sh "echo -e \"$NPM_USERNAME\n$NPM_PASSWORD\n$NPM_EMAIL\" | npm login"
            sh 'npm publish'
         }
      }
   }
}
