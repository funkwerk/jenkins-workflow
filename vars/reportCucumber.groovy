def call(report) {
  step([
    $class: 'CucumberReportPublisher', fileExcludePattern: '', fileIncludePattern: report,
    ignoreFailedTests: false, jenkinsBasePath: '', jsonReportDirectory: '',
    missingFails: false, parallelTesting: false, pendingFails: false, skippedFails: false, undefinedFails: false])
}
