def call(Closure body) {
  wrap([$class: 'Xvfb']) {
    body()
  }
}
