def call(command) {
  catchStage(command) {
    sh command
  }
}

def call(name, command) {
  catchStage(name) {
    sh command
  }
}
