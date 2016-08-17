def call() {
  try {
    notifyStash()
  } catch(error) {
    print "Notifying Stash failed: ${error}"
  }
  gitClean()
  checkout scm
  gitEnv()
}
