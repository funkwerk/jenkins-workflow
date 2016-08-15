def call(Closure body) {
  wrap([$class: 'AnsiColorBuildWrapper']) {
    body()
  }
}
