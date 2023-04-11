def gv
pipeline{
agent {label 'agent2'}
parameters{
    choice(name:'Version' , choices:['1.0','1.1','1.2'], description: '')
    booleanParam(name:'executeTest',defaultValue: true,description: '')
}
stages{
stage('init'){
    steps{
         script{
           gv = load 'script.groovy'
        }
    }
}
stage('build'){
    steps{
        script{
            gv.buildApp()
        }
    }
}
stage('test'){
    when {
        expression{
            params.executeTest
        }
    }
    steps{
        script{
            gv.testApp()
        }
    }
}
stage('deploy'){
    input{
    message "select environment to deploy the app"
    ok "Done"
        parameters{
        choice(name:'ENV', choices:['dev','staging','prod'],description:"")
        }
    }
    steps{
        script{
            gv.deployApp()
            echo "env is ${ENV}"
        }
    }
}
}
}
