pipeline {
  agent {
    kubernetes {
      yamlFile 'cicd/ci/imageBuildPush/jenkins-kanikoAgentPodTemplate.yaml'
    }
  }

  environment {
    IMAGE_NAME   = "amitgiri13/habittracker"
    IMAGE_TAG    = "${env.BUILD_NUMBER}"
    FULL_IMAGE   = "${IMAGE_NAME}:${IMAGE_TAG}"
    LATEST_IMAGE = "${IMAGE_NAME}:latest"
  }

  stages {

    stage('Checkout') {
      steps {
        git(
          url: 'https://github.com/amitgiri-13/HabitTracker.git',
          credentialsId: 'github-cred',
          branch: 'main'
        )
      }
    }

    stage('Validate Dockerfile') {
      steps {
        sh '''
          echo "==> Checking Dockerfile exists..."
          if [ ! -f Dockerfile ]; then
            echo "ERROR: Dockerfile not found in workspace root!"
            exit 1
          fi
          echo "==> Dockerfile found. Contents:"
          cat Dockerfile
        '''
      }
    }

    stage('Build & Push with Kaniko') {
      steps {
        container(name: 'kaniko', shell: '/busybox/sh') {
          sh '''#!/busybox/sh
            echo "==> Starting Kaniko build..."
            echo "==> Image: ${FULL_IMAGE}"

            /kaniko/executor \
              --context="${WORKSPACE}" \
              --dockerfile="${WORKSPACE}/Dockerfile" \
              --destination="${FULL_IMAGE}" \
              --destination="${LATEST_IMAGE}" \
              --cache=true \
              --cache-ttl=24h \
              --compressed-caching=false \
              --snapshot-mode=redo \
              --log-format=text \
              --verbosity=info

            echo "==> Build and push complete!"
          '''
        }
      }
    }

  }

  post {
    success {
      echo "Pipeline succeeded! Image pushed: ${env.FULL_IMAGE}"
    }
    failure {
      echo "Pipeline FAILED. Check logs above for details."
    }
    always {
      cleanWs()
    }
  }
}