timestamps{
    node('maven-oc'){
        stage('Checkout Git'){
            scm
        }

	    stage("Maven Build") {
            sh 'mvn -B clean package'
        }

        stage("Run Gatling") {
            sh 'mvn gatling:test'
        }

        stage("Gatling Archive") {
            gatlingArchive()
        }
    }
}