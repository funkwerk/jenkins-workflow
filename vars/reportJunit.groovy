def call(report) {
  step([$class: 'JUnitResultArchiver', testResults: report])
}
