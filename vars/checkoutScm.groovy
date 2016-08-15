def call() {
  gitClean()
  checkout scm
  gitEnv()
  try {
    notifyStash()
  } catch(error) {
    print "Notifying Stash failed: ${error}"
  }
}
