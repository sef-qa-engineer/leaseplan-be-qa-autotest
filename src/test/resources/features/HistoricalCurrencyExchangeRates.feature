 Feature: Historical exchange rate conversion
   Background: Get historical rates for a specific day
     # Endpoint: https://api.currencylayer.com/historical
     # access_key : [Required] Your API Access Key.
     # source : [optional] Specify a Source Currency other than the default USD. Supported on the Basic Plan and higher.
     # currencies : [optional] Specify a comma-separated list of currency codes to limit your API response to specific currencies.

  Scenario: Historical single source to single currency exchange rates
    Given my historical source currency is EUR
    And my historical exchange currency is USD
    And date is 2021-05-01
    When I lookup for historical currency exchange rates
    Then historical currency exchange rate lookup is successful
    And historical exchange rate is true
    And I will have historical timestamp
    And I will have the historical exchange rates


   Scenario: Historical single source to single currency exchange rates, using lowercase letters for currency code
     Given my historical source currency is eur, in lowercase
     And my historical exchange currency is USD
     And date is 2021-05-01
     When I lookup for historical currency exchange rates
     Then historical currency exchange rate lookup is successful
     And historical exchange rate is true
     And I will have historical timestamp
     And I will have the historical exchange rates


   Scenario: Historical single source to single currency exchange rates, using lowercase letters for currency code
     Given my historical source currency is EUR
     And my historical exchange currency is usd, in lowercase
     And date is 2021-05-01
     When I lookup for historical currency exchange rates
     Then historical currency exchange rate lookup is successful
     And historical exchange rate is true
     And I will have historical timestamp
     And I will have the historical exchange rates


   Scenario: Historical source currency is set to null
     Given my historical source currency is null
     And my historical exchange currency is USD
     And date is 2021-05-01
     When I lookup for historical currency exchange rates
     Then historical currency exchange rate lookup is successful
     And historical exchange rate is true
     And I will have current timestamp
     And historical source currency is set to default USD
     And I will have the historical exchange rates


   Scenario: Historical exchange currency is set to null
     Given my historical source currency is EUR
     And my historical exchange currency is null
     And date is 2021-05-01
     When I lookup for historical currency exchange rates
     Then historical currency exchange rate lookup is successful
     And historical exchange rate is true
     And I will have historical timestamp
     And I will have the historical exchange rates


   Scenario: Historical source and exchange currency are both set to null
     Given my historical source currency is null
     And my historical exchange currency is null
     And date is 2021-05-01
     When I lookup for historical currency exchange rates
     Then historical currency exchange rate lookup is successful
     And historical exchange rate is true
     And I will have historical timestamp
     And historical source currency is set to default USD
     And I will have the historical exchange rates


   Scenario: Historical single source to multiple currencies exchange rates
     Given my historical source currency is EUR
     And date is 2021-05-01
     When I lookup for EUR to multiple currencies historical exchange rates
       |Exchange Currency |
       |USD               |
       |JPY               |
       |GBP               |
       |AUD               |
       |CAD               |
       |CHF               |
       |CNY               |
       |NZD               |
     When I lookup for historical currency exchange rates
     And I will have historical timestamp
     And I will have the historical exchange rates


   Scenario: Historical multiple source currencies to single currency exchange rates
     Given my historical source currencies are 8 major currencies:
       |Source Currency   |
       |USD               |
       |JPY               |
       |GBP               |
       |AUD               |
       |CAD               |
       |CHF               |
       |CNY               |
       |NZD               |
     And my historical exchange currency is EUR
     And date is 2021-05-01
     When I lookup for historical currency exchange rates
     Then historical currency exchange rate lookup is unsuccessful
     And I will not have historical timestamp
     And I will not have the historical exchange rates


   Scenario: Historical multiple source currencies to multiple currencies exchange rates
     Given I have 8 source currencies and 8 exchange historical currencies:
       |Source Currency   |Exchange Currency  |
       |USD               |EUR                |
       |JPY               |CNY                |
       |GBP               |CAD                |
       |AUD               |GBP                |
       |CAD               |NZD                |
       |CHF               |EUR                |
       |CNY               |JPY                |
       |NZD               |AUD                |
     And date is 2021-05-01
     When I lookup for historical currency exchange rates
     Then historical currency exchange rate lookup is unsuccessful
     And I will not have historical timestamp
     And I will not have the historical exchange rates


   Scenario: Source currency code is not standard 3 letter code
     Given my historical source currency is EURO
     And my historical exchange currency is USD
     And date is 2021-05-01
     When I lookup for historical currency exchange rates
     Then historical currency exchange rate lookup is unsuccessful
     And I will not have historical timestamp
     And I will not have the historical exchange rates


   Scenario: Exchange currency code is not standard 3 letter code
     Given my historical source currency is EUR
     And my historical exchange currency is DOLLAR
     And date is 2021-05-01
     When I lookup for historical currency exchange rates
     Then historical currency exchange rate lookup is unsuccessful
     And I will not have historical timestamp
     And I will not have the historical exchange rates


   Scenario: Source and exchange currency codes are not standard 3 letter code
     Given my historical source currency is EURO
     And my historical exchange currency is DOLLAR
     And date is 2021-05-01
     When I lookup for historical currency exchange rates
     Then historical currency exchange rate lookup is unsuccessful
     And I will not have historical timestamp
     And I will not have the historical exchange rates


   Scenario: Historical single source to single currency exchange rates with invalid access key
     Given my historical source currency is EUR
     And my historical exchange currency is USD
     And my historical access key is invalid
     When I lookup for historical currency exchange rates
     Then historical currency exchange rate lookup is unsuccessful
     And I will not have historical timestamp
     And I will not have the historical exchange rates

   Scenario: Historical single source to single currency exchange rates with invalid date format
     Given my historical source currency is EUR
     And my historical exchange currency is USD
     And date format is invalid
     When I lookup for historical currency exchange rates
     Then historical currency exchange rate lookup is unsuccessful
     And I will not have historical timestamp
     And I will not have the historical exchange rates