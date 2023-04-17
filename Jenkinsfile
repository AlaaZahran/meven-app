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
             // Use build-helper-maven-plugin to parse the version
                    sh 'mvn build-helper:parse-version org.apache.maven.plugins:maven-help-plugin:3.2.0:evaluate -Dexpression=project.version'
                    // Load the parsed version information from the properties file
                    def props = readProperties file: 'target/maven-archiver/pom.properties'
                    // Extract the parsed version information and increment the next incremental version segment
                    def majorVersion = props.getProperty('parsedVersion.majorVersion')
                    def minorVersion = props.getProperty('parsedVersion.minorVersion')
                    def incrementalVersion = (props.getProperty('parsedVersion.nextIncrementalVersion') as int) + 1
                    // Set the new version as an environment variable
                    env.IMAGE_TAG = "${majorVersion}.${minorVersion}.${incrementalVersion}"
                    // Set the new version in the pom.xml file
                    sh "mvn versions:set -DnewVersion=${env.IMAGE_TAG} versions:commit"
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
