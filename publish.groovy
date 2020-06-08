pipeline {
   agent any
   stages {
      stage('test') {
         steps {
            echo 'publishing'
            withCredentials([[$class: 'UsernamePasswordMultiBinding', credentialsId: '5af8e9bd-34cf-4c11-99b3-5d6b62d1eeb6', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD']]) {
              sh 'npm login'
              expect {
                "Username:" {
                  send "$usernameVariable\r";
                  expect {
                    "Password:" {
                      send "$passwordVariable\r";
                      expect{
                        "Email: (this IS public)" {
                          send "galfrano@gmail.com\r";
                          expect eof
                          wait
                        }
                      }
                    }
                  }
                }
              }
              sh 'npm publish'
            }
         }
      }
   }
}
