def call(Closure body) {
  node('developer') {
    try {
      withEnv(["BUILD_NODE=${env.NODE_NAME}"]) {
        body()
      }
    } catch(error) {
      echo "Error: ${error}"
      throw error
    }
  }
}
