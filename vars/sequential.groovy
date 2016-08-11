def call(list, closure) {
  for (item in list) {
    closure(item)
  }
}
