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
	
	node{
		descargarFuentes()
		clean()
	}