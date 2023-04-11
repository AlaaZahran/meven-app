def buildApp(){
    echo 'building application ...'
}
def testApp(){
    echo 'testing application ...'
}
def deployApp(){
    echo 'deploying application ...'
    echo "deployin on ${Version}"
}
return this
