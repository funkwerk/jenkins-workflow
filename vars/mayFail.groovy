def call(Closure body) {
  call('unnamed stage', body)
}

def call(String name, Closure body) {
  ansiColor {
    timeStamped {
      catchError {
        try {
          body()
        } catch(error) {
          errors.add name

          if (env.OPTIONAL_STAGE) {
            currentBuild.result = 'UNSTABLE'
          } else {
            currentBuild.result = 'FAILURE'
            throw error
          }
        }
      }
    }
  }
}
