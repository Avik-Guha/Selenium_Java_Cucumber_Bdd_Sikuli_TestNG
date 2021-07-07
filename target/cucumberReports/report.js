$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("file:src/test/resources/features/TestDropdown.feature");
formatter.feature({
  "name": "Test Dropdown",
  "description": "  Feature To Test Dropdown",
  "keyword": "Feature",
  "tags": [
    {
      "name": "@Sanity"
    },
    {
      "name": "@TestDropdown"
    }
  ]
});
formatter.scenario({
  "name": "Verify All Text In Dropdown",
  "description": "",
  "keyword": "Scenario",
  "tags": [
    {
      "name": "@Sanity"
    },
    {
      "name": "@TestDropdown"
    },
    {
      "name": "@TestDropdown_VerifyAllText"
    }
  ]
});
formatter.before({
  "status": "passed"
});
formatter.step({
  "name": "Launch Application",
  "keyword": "Given "
});
formatter.match({
  "location": "steps.CommonStepDefinitions.launch_Application()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "Verify All Text in Skills dropdown field",
  "keyword": "Then "
});
formatter.match({
  "location": "steps.Register_SD.verify_All_Text_in_Skills_dropdown_field()"
});
formatter.result({
  "status": "passed"
});
formatter.after({
  "status": "passed"
});
});