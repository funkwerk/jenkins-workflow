def call(command) {
  catchStage(command) {
    sh command
  }
}
