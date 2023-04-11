def buildJar(){
   echo 'building jar file ...'
           sh 'mvn package'
}
def buildPushImage(){
    echo ' building docker image .....'
     withCredentials([usernamePassword(credentialsId: 'dockerhub', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]) {
     sh 'docker build . -t alaa0ali/mavnapp:1.2'
     echo "username is $USERNAME "
     sh "echo $PASSWORD | docker login -u $USERNAME --password-stdin" 
     echo "push docker image to dockerhub"
     sh 'docker push alaa0ali/mavnapp:1.2'
}
return this
