pipeline {
   agent any
   stages {
      stage('test') {
         steps {
            echo 'publishing'
            sh 'npm publish'
         }
      }
   }
}
