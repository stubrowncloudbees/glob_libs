def call(body) {

    def pipelineParams = [:]
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = config
    body()

    def myEnvName = "${pipelineParams.envName}"

    pipeline {
        agent {
            kubernetes {
                label 'my-pod-template'
                defaultContainer 'jnlp'
                yaml """
                    apiVersion: v1
                    kind: Pod
                    metadata:
                      labels:
                        some-label: some-label-value
                    spec:
                      containers:
                      - name: maven
                        image: maven:alpine
                        command:
                        - cat
                        tty: true
                      - name: busybox
                        image: busybox
                        command:
                        - cat
                        tty: true
                """
            }
        }
        stages {
            stage('Run maven') {
                steps {
                    container('maven') {
                        sh 'mvn -version'
                        echo "${myEnvName}  "
                    }
                    container('busybox') {
                        script{
                            try {
                                sh 'exit 1'
                            }
                            catch (exc){
                                echo 'somthing failed'

                            }
                        }
                    }
                }
            }
        }
    }


}