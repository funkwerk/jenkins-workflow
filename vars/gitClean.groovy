def call() {
  timeout(time: 600, unit: 'SECONDS') {
    if (fileExists('.git')) {
      echo 'Found Git repository: using Git to clean the tree.'
      sh 'git reset --hard'
      sh 'git clean -ffdx -e ".*/"'
      sh 'git submodule foreach --recursive git reset --hard'
      sh 'git submodule foreach --recursive git clean -ffdx'
    } else {
      echo 'No Git repository found: using deleteDir() to wipe clean'
      deleteDir()
    }
  }
}
