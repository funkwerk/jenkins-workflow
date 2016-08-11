def call() {
  sh 'git rev-parse HEAD > git-revision.tmp'

  env.REVISION = readFile('git-revision.tmp').trim()
  env.BRANCH = "${env.BRANCH_NAME}"
}
