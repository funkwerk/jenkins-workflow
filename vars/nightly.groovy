def call(Closure body) {
  properties(
    [[$class: 'ParametersDefinitionProperty',
      parameterDefinitions:
        [[$class: 'BooleanParameterDefinition', defaultValue: false, description: 'Nightly Build', name : 'NIGHTLY']]]])

  if (getBinding().hasVariable("NIGHTLY")) {
    if (Boolean.parseBoolean(NIGHTLY)) {
      body()
    }
  }
}
