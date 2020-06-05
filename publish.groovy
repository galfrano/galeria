/*pipeline {
   agent any
   stages {
      stage('test') {
         steps {
           echo 'running unit tests'
            sh 'npm install npx jest'
            sh 'npx jest'
            echo 'publishing'
            sh 'npm publish'
         }
      }
   }
}
*/

stage ('Publish') {
  withNPM(npmrcConfig: 'galeria') {
    sh 'npm publish'
  }
}
