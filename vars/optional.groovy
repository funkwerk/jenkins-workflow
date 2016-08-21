def call(Closure body) {
  withEnv(['OPTIONAL_STAGE=errors_are_not_fatal']) {
    body()
  }
}
