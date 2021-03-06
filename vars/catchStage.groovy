def call(String name, Closure body) {
  try {
    stage(name) {
      mayFail(name) {
        body()
      }
    }
  } catch(hudson.AbortException error) {
    // probably called within a parallel step, so it's okay that it fails here
    // TODO: find a better solution if we are in a parallel branch or not
  }
}
