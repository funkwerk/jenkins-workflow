def add(String name) {
  if (currentBuild.description == null) {
    currentBuild.description = ""
  }
  if (currentBuild.description != "") {
    currentBuild.description += ", "
  }
  currentBuild.description += "${name} failed"
}
