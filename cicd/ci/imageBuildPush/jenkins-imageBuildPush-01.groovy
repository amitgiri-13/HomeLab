def buildAndPushImage(config) {
  container(name: 'kaniko', shell: '/busybox/sh') {
    sh """#!/busybox/sh
      echo "==> Building image: ${config.fullImage}"

      /kaniko/executor \
        --context="${WORKSPACE}" \
        --dockerfile="${WORKSPACE}/${config.dockerfile}" \
        --destination="${config.fullImage}" \
        --destination="${config.latestImage}" \
        --cache=true \
        --cache-ttl=24h \
        --compressed-caching=false \
        --snapshot-mode=redo \
        --log-format=text \
        --verbosity=info

      echo "==> Build complete!"
    """
  }
}

pipeline {
  agent {
    kubernetes {
      yaml readFile(params.POD_TEMPLATE)
    }
  }

  parameters {
    string(name: 'GIT_REPO', defaultValue: 'https://github.com/amitgiri-13/HabitTracker.git')
    string(name: 'GIT_BRANCH', defaultValue: 'main')
    string(name: 'IMAGE_NAME', defaultValue: 'amitgiri13/habittracker')
    string(name: 'DOCKERFILE_PATH', defaultValue: 'Dockerfile')
    string(name: 'POD_TEMPLATE', defaultValue: 'cicd/ci/imageBuildPush/jenkins-kanikoAgentPodTemplate.yaml')
  }

  environment {
    IMAGE_TAG    = "${env.BUILD_NUMBER}"
    FULL_IMAGE   = "${params.IMAGE_NAME}:${IMAGE_TAG}"
    LATEST_IMAGE = "${params.IMAGE_NAME}:latest"
  }

  stages {
    stage('Checkout') {
      steps {
        git(
          url: params.GIT_REPO,
          branch: params.GIT_BRANCH,
          credentialsId: 'github-cred'
        )
      }
    }

    stage('Validate Dockerfile') {
      steps {
        sh """
          echo "==> Checking Dockerfile..."
          if [ ! -f ${params.DOCKERFILE_PATH} ]; then
            echo "ERROR: Dockerfile not found!"
            exit 1
          fi
        """
      }
    }

    stage('Kaniko Build') {
      steps {
        script {
          buildAndPushImage([
            dockerfile : params.DOCKERFILE_PATH,
            fullImage  : env.FULL_IMAGE,
            latestImage: env.LATEST_IMAGE
          ])
        }
      }
    }
  }

  post {
    success {
      echo "Image pushed: ${env.FULL_IMAGE}"
    }
    failure {
      echo "Pipeline failed"
    }
    always {
      cleanWs()
    }
  }
}