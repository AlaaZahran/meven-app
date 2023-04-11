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
    steps{
        script{
            env.ServerNum= input message:"what is sever num ?" ,ok :"done" , parameters: [choice(name: "One" , choices:["1","2","3"] , description:'' )]
            gv.deployApp()
            echo "env is ${ENV}"
            echo "server numder is ${ServerNum}"
        }
    }
}
}
}
