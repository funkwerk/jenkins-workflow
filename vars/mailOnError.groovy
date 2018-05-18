def call(String cc='') {
  mailOnError(cc: cc)
}

def call(Map map) {
  def cc = map.cc ? map.cc : ''

  try {
    node('master') {
      if (currentBuild.result == null) {
        currentBuild.result = 'SUCCESS'
      } else if (currentBuild.result != 'UNSTABLE') {
        currentBuild.result = 'FAILED'
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

  stage('failure') {
    print "Culprits: ${to}\n\n"

    def body = "Failing Stages:\n";
    body += currentBuild.description + "\n\n"
    body += "Changesets:\n" + changeSets() + "\n"
    body += "Please go to ${env.BUILD_URL}.\n\n";
    body += "Culprits: ${to}\n\n" + buildCauses()

    mail(
      to: to,
      cc: cc,
      subject: "${env.JOB_NAME} - Build ${env.BUILD_NUMBER} Failed!",
      body: body)
  }
}

@NonCPS
def changeSets() {
  def text = ""
  for (changeSetList in currentBuild.changeSets) {
    for (changeSet in changeSetList) {
      text += " - ${changeSet.author.fullName} ${changeSet.msg} (${changeSet.commitId})\n"
    }
  }
  return text
}

@NonCPS
def buildCauses() {
    def text = ""
    for (cause in currentBuild.rawBuild.getCauses()) {
      text += " - ${cause.getShortDescription()}\n"
    }
    return text
}
