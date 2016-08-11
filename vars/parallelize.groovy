def call(list, closure) {
  variants = [:]

  for (item in list) {
    variants[item] = { closure(item) }
  }
  parallel variants
}
