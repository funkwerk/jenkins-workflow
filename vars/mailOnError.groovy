// TODO: call with map...so that cc: foo@bar.de

def call(name='') {
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
    cc: name,
    subject: "${env.JOB_NAME} - Build ${env.BUILD_NUMBER} Failed!",
    body: body)
}
