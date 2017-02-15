def call(list, closure) {
  variants = [:]

  list.each{ item -> variants[item] = { closure(item) } }
  parallel variants
}
