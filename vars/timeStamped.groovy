def call(Closure body) {
  wrap([$class: 'TimestamperBuildWrapper']) {
    body()
  }
}
