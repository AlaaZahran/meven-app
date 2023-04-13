#!/usr/bin/env groovy
@Library('jenkins-shared-library')
def gv
pipeline{
agent {label 'docker'}
tools{
    maven 'Maven'
}
stages{
stage("init"){
    steps{
        script{
          gv = load 'script.groovy'
        }
    }
}    
stage('build jar'){
    steps{
         script{
           buildJar()
        }
    }
}
stage('build and push docker image '){
    steps{
        script{
            buildDockerImage "alaa0ali/mavnapp:3.0"
            dockerLogin()
            pushDockerImage "alaa0ali/mavnapp:3.0"
           } 
        }
    }
stage('deploy '){
    steps{
        script{
            gv.deployAPP()
           } 
        }
    }    
}
}
