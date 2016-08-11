def call(Closure body) {
  try {
    body()
  } catch(error) {
    // ignore error
  }
}
