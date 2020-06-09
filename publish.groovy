
def authenticate(){
  withCredentials([[$class: 'UsernamePasswordMultiBinding', credentialsId: '5af8e9bd-34cf-4c11-99b3-5d6b62d1eeb6	', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD']]){
    def token = (username+":"+password).bytes.encodeBase64().toString()
    sh 'npm config set //nexus.mgmt.conrad24.com/nexus/repository/npm-local/:_authToken ${token}'
  }
}

pipeline {
   agent any
   stages {
      stage('publish') {
         steps {
            echo 'publishing'
            authenticate()
            sh 'npm publish'
         }
      }
   }
}
