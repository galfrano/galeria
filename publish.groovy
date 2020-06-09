
def publish(){
  withCredentials([[$class: 'UsernamePasswordMultiBinding', credentialsId: '5af8e9bd-34cf-4c11-99b3-5d6b62d1eeb6	', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD']]){
    def token = ("${USERNAME}:${PASSWORD}").bytes.encodeBase64().toString()
    echo token
    sh 'npm config set //registry.npmjs.org/:_auth ${token}'
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
