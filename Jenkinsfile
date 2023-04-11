pipeline{
agent {label 'docker'}
tools{
    maven 'maven.3.9.1'
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
           withCredentials([usernamePassword(credentialsId: 'dockerhub' , usernameVariable:'UESER' , passwordVariable:'PASS' )]){
             sh 'docker build . -t alaa0ali/mavnapp:1.2'
             sh "echo$PASS|docker login -u $USER --password-stdin" 
             echo'push image to dockerhub ....'
             sh 'docker push alaa0ali/mavnapp:1.2'
           } 
        }
    }
}


}}
