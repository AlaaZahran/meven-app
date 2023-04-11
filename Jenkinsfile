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
           withCredentials([usernamePassword(credentialsId: 'dockerhub' , usernameVariable:'UESER' , passwordVariable:'PASS' )]){
             sh 'docker build . -t alaa0ali/mavnapp:1.2'
               echo "username is ${USER} "
               sh "docker login -u ${USER} -p ${PASS}" 
             sh 'docker push alaa0ali/mavnapp:1.2'
           } 
        }
    }
}


}}
