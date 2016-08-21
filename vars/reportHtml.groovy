def call(name, path) {
  publishHTML(target: [reportDir:folder, reportFiles:'index.html', reportName: name])
}
