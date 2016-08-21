def call(Map map) {
  assert map.path != null
  assert map.name != null

  publishHTML(target: [reportDir: map.path, reportFiles: 'index.html', reportName: map.name])
}
