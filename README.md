# Leaseplan BE QA Automation Test Suite
QA Engineer: JNN Niez

This automation project demonstrates the capability of the following libraries, frameworks and technologies in testing public API endpoints:
- Serenity
- Cucumber
- RestAssured
- Maven
- Java

### API Under Test: currencylayer API ()
Available Public APIs:
- Get real time exchange rate data —> https://api.currencylayer.com/live
- Get historical rates for specified date —> https://api.currencylayer.com/historical?date=YYYY-MM-DD 
- Convert specified currencies —> https://api.currencylayer.com/convert?from=EUR&to=GBP&amount=100 
- Request rates for a specified duration —> https://api.currencylayer.com/timeframe?start_date=2015-01-01&end_date=2021-05-01
- Request margin and percentage changes —> https://api.currencylayer.com/change?currencies=USD,EUR
```
Complete documentation is available at https://currencylayer.com/documentation
```
The API requires access token. There are endpoints which requires paid subscription.
In the test.properties, an access key is provided for testing purposes.
```sh
test.access_key=7adf78e186bd2ad4f1065c690013c409
```

### How to use the automation test suite?
- Use either IntelliJ community or enterprise edition as IDE.
- Clone or download the the automation test suite.
- Make sure the following dependencies are installed:
```sh
serenity-cucumber
serenity-rest-assured
cucumber-java
datatable (io.cucumber)
serenity-junit
serenity-emailer
cucumber-junit
maven-surefire-plugin
```
- In Intellij, right click on features from the directory tree panel, then click on Run "All Features in: features"
- You can also individually select and run each feature files.


### Directory Structure

```sh
/src — /test —  /java
          |.       | — /leaseplan
          |.             | — /actions
          |.             | — /stepdefinitions
          |.             | — BaseTest.java
          |.             | — Currency.java
          |.             | — CurrencyUtility.jaja
          |.             | — CustomDate.java
          |.  
          | — /resources
                 | — /features
                 | — /testlogs
                 | — test.properties 
```
