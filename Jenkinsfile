#!/usr/bin/env groovy
@Library('jenkins-shared-library')
def gv
pipeline{
agent {label 'docker'}
tools{
    maven 'Maven'
}
stages{
    
stage("increment version"){
    steps{
        script{
          echo 'increment version ...'
          sh "mvn build-helper:parse-version versions:set -DnewVersion=${parsedVersion.majorVersion}.${parsedVersion.minorVersion}.${parsedVersion.nextIncrementalVersion} versions:commit"
          def matcher=readFile('pom.xml')=~ '<version>(.+)</version>'
          def version=matcher[0][1]
          echo "version is $version"
          env.IMAGE_NAME="$version-$BUILD_NUMBER"
        }
    }
}
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
            buildDockerImage "alaa0ali/mavnapp:${IMAGE_NAME}"
            dockerLogin()
            pushDockerImage "alaa0ali/mavnapp:${IMAGE_NAME}"
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
