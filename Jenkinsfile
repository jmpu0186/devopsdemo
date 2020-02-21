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
	sh "docker build -t 434449356981.dkr.ecr.sa-east-1.amazonaws.com/docker-in-aws/demo:${env.BUILD_ID} -f ${WORKSPACE}/Dockerfile ."

}

def pushimage(){
	stage "pushimage"
	docker.withRegistry('https://434449356981.dkr.ecr.sa-east-1.amazonaws.com', 'ecr:sa-east-1:AWS_ECR') {
            sh "docker push 434449356981.dkr.ecr.sa-east-1.amazonaws.com/docker-in-aws/demo:${env.BUILD_ID}"
        }
}
	
	def testunit(){
		 stage("testunit") {
                             
                                  docker.image('maven:3-alpine').inside('-v /root/.m2:/root/.m2'){
                    			        sh 'mvn test'
                    			        step([$class: 'JacocoPublisher', testResults: '**/target/surefire-reports/TEST-*.xml'])
                    			    }
                    		   
                       }
	}
	
	def pruebasfuncionales(){
		    parallel IniciaAplicacion: {
			    try{	
			    	sh "docker run --name=demodevops-${env.BUILD_ID} -p 8080:8080 434449356981.dkr.ecr.sa-east-1.amazonaws.com/docker-in-aws/demo:${env.BUILD_ID}"
			    }catch(err){
				    
			    }
		    }, RealizaPruebaRendimiento: {
			build job: 'jobjmter'
			sh "docker stop demodevops-${env.BUILD_ID}"
		    }
	}
	
	node{
		descargarFuentes()
		clean()
		testunit()
		empaquetar()
		buildimage()
		pruebasfuncionales()
		//pushimage()
	}
