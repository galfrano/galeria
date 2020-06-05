/*pipeline {
   agent any
   stages {
      stage('test') {
         steps {
           echo 'running unit tests'
            sh 'npm install npx jest'
            sh 'npx jest'
            echo 'publishing'
            sh 'npm adduser --registry=https://nexus.mgmt.conrad24.com/nexus/repository/npm-local/ --always-auth'
            sh 'npm publish'
         }
      }
   }
}
*/

stage ('Publish') {
  withNPM(npmrcConfig: 'regex-pdp') {
    sh 'npm publish'
  }
}
