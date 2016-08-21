def call(String cc='') {
  mailOnError(cc: cc)
}

def call(Map map) {
  def cc = map.cc ? map.cc : ''

  try {
    developer {
      if (currentBuild.result == null) {
        currentBuild.result = 'SUCCESS'
      }
      notifyStash()
    }
  } catch (error) {
    print "Notifying Stash failed: ${error}"
  }
  if (currentBuild.result != 'FAILURE') {
    return
  }
  def to = emailextrecipients([
    [$class: 'CulpritsRecipientProvider'],
    [$class: 'DevelopersRecipientProvider'],
    [$class: 'RequesterRecipientProvider']])

  stage 'failure'

  print "Culprits: ${to}\n\n"

  def body = "Failing Stages:\n";
  body += currentBuild.description
  body += "\n\n"
  body += "Changesets:\n"
  for (changeSet in currentBuild.changeSets) {
    body += " - ${changeSet}\n"
  }
  body += "\n"
  body += "Please go to ${env.BUILD_URL}.\n\n";
  body += "Culprits: ${to}\n\n"
  for (cause in currentBuild.rawBuild.getCauses()) {
    body += " - ${cause.getShortDescription()}\n"
  }

  mail(
    to: to,
    cc: cc,
    subject: "${env.JOB_NAME} - Build ${env.BUILD_NUMBER} Failed!",
    body: body)
}
