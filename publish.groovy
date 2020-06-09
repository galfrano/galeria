
def publish(){
  withCredentials([[$class: 'UsernamePasswordMultiBinding', credentialsId: 'token', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD']]){
    def token = (username+":"+password).bytes.encodeBase64().toString()
    echo token
    sh 'npm config set //registry.npmjs.org/:_authToken ${PASSWORD}'
    sh 'npm publish'
  }
}

pipeline {
   agent any
   stages {
      stage('publish') {
         steps {
            echo 'publishing'
            publish()
         }
      }
   }
}
