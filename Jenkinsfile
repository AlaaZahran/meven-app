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
          sh "mvn build-helper:parse-version versions:set \
          -DnewVersion=\\\${parasedVersion.majorVersion}.\\\${parasedVersion.minorVersion}.\\\${parasedVersion.nextIncrementalVersion} versions:commit"
          def matcher=readFile('pom.xml')=~ '<version>(.+)</version>'
          def version=matcher[0][1]
          echo "version is $version"
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
