def call(Closure body) {
  call('unnamed stage', body)
}

def call(String name, Closure body) {
  timeStamped {
    catchError {
      try {
        body()
      } catch(error) {
        errors.add name

        throw error
      }
    }
  }
}
