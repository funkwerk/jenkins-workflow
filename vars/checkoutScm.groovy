def call() {
  gitClean()
  checkout scm
  gitEnv()
}
