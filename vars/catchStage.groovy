def call(String name, Closure body) {
  stage name
  mayFail(name) {
    body()
  }
}
