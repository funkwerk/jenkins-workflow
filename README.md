# jenkins-workflow

Contains handy [groovy workflow-libs scripts for Jenkins](https://github.com/jenkinsci/workflow-plugin/blob/master/README.md).
Goal is to keep `Jenkinsfile` as concise and readable as possible.

## Usage

Clone this project into `$JENKINS_HOME/workflow-libs`. After that restart Jenkins. Then Pipeline as well as Multi-Branch-Pipeline Builds are able to use these steps.

## Simple Example for a Rake Project

```groovy
#!groovy

developer {
  checkoutScm()
  rake 'unittest[--report=build/report.xml]'
  reportJunit 'build/report.xml'  // will publish the test report
  optional { rake 'audit' }  // to mark stages which are not critical
  rake 'uml'
  reportHtml(name: 'UML', path: 'build/html')  // will upload an arbitrary HTML Report
  rake 'test variant=release'
  rake 'deploy prefix=export'
}
```


## References

 - [pipeline-examples](https://github.com/jenkinsci/pipeline-examples)
 - [Jenkins Pipeline Best Practices](https://github.com/jenkinsci/pipeline-examples/blob/master/docs/BEST_PRACTICES.md)
 - [CloudBees Top 10 Best Practices](https://www.cloudbees.com/blog/top-10-best-practices-jenkins-pipeline-plugin)
