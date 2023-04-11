def gv
pipeline{
agent {label 'agent2'}
parameters{
    choice(name:'Version' , choices:['1.0','1.1','1.2'], description: '')
    booleanParam(name:'execute-test-stage',defaultvalue: true,description: '')
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
            execute-test-stage
        }
    }
    steps{
        script{
            gv.testApp()
        }
    }
}
stage('deploy'){
    steps{
        script{
            gv.deployApp()
        }
    }
}
}
}
