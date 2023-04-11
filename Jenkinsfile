pipeline{
agent {label 'docker'}
tools{
    maven 'Maven'
}
stages{
stage('build jar'){
    steps{
         script{
           echo 'building jar file ...'
           sh 'mvn package'
        }
    }
}
stage('build image '){
    steps{
        script{
            echo ' building docker image .....'
           withCredentials([usernamePassword(credentialsId: 'dockerhub', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]) {
             sh 'docker build . -t alaa0ali/mavnapp:1.2'
             echo "username is $USERNAME "
             sh "echo $PASSWORD | docker login -u $USERNAME --password-stdin" 
             sh 'docker push alaa0ali/mavnapp:1.2'
           } 
        }
    }
}


}}
