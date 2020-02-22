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
	
	def pruebasfuncionales_old(){
		stage "pruebas funcionales"
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
	
	def pruebasfuncionales(){
		stage ("pruebas funcionales"){
		 
			    	sh "docker run --name=demodevops-${env.BUILD_ID} --env APP_VERSION=${env.BUILD_ID} -p 8080:8080 -d 434449356981.dkr.ecr.sa-east-1.amazonaws.com/docker-in-aws/demo:${env.BUILD_ID}"
					build job: 'jobjmter'
					sh "docker stop demodevops-${env.BUILD_ID}"
		    }
	}
	
	def autorizacionDespliegue() {
     stage "autorización"
     
       timeout(time: 180, unit: 'MINUTES') {
            input message: "¿Desea desplegar el componente?"
        }
     }
     
     def desplegarEnInstanciaEC2(){
     	stage ("despliegue componente en instancia"){
     	def version=env.BUILD_ID
	    	sshagent(credentials : ['AWS_EC2_INSTANCIA1']) {
        		def commandToRun = "/home/ec2-user/stopApp.sh; docker run --env APP_VERSION=${VERSION} -p 80:8080 -d 434449356981.dkr.ecr.sa-east-1.amazonaws.com/docker-in-aws/demo:${VERSION}"
        		sh "ssh -t -t ec2-user@ec2-18-228-117-167.sa-east-1.compute.amazonaws.com -o StrictHostKeyChecking=no /bin/bash -c '\"${commandToRun}\"'"
    		} 
    	}
	}
	
	node{
		descargarFuentes()
		clean()
		testunit()
		empaquetar()
		buildimage()
		pruebasfuncionales()
		pushimage()
		autorizacionDespliegue()
		desplegarEnInstanciaEC2()
	}
