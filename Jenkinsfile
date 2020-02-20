	def descargarFuentes() {
     stage "descarga fuente"
     checkout scm
     echo "SE DESCARGA FUENTES DE GIT"
    }
	
	def clean() {
     stage "clean"
     docker.image('maven:3-alpine').inside('-v /root/.m2:/root/.m2'){
        sh 'mvn clean'
     }
     
    }
	
	def empaquetar() {
     stage "empaquetar"
     docker.image('maven:3-alpine').inside('-v /root/.m2:/root/.m2'){
		sh 'mvn package -DskipTests -Ddockerfile.skip'
     }
	}

def buildimage(){
	stage "buildimage"
	sh "docker build --build-arg APP_NAME=devopsapp -t 434449356981.dkr.ecr.sa-east-1.amazonaws.com/docker-in-aws/demo:${env.BUILD_ID} -f ${WORKSPACE}/Dockerfile ."

}
	
	def testunit(){
		 stage("testunit") {
                             
                                  docker.image('maven:3-alpine').inside('-v /root/.m2:/root/.m2'){
                    			        sh 'mvn test'
                    			        step([$class: 'JacocoPublisher', testResults: '**/target/surefire-reports/TEST-*.xml'])
                    			    }
                    		   
                       }
	}
	
	node{
		descargarFuentes()
		clean()
		testunit()
		empaquetar()
		buildimage()
	}
