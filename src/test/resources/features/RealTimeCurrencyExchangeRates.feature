 Feature: Real time exchange rate conversion
   Background: Get the most recent and real time exchange rate data
     # Endpoint: https://api.currencylayer.com/live
     # access_key : [Required] Your API Access Key.
     # source : [optional] Specify a Source Currency other than the default USD. Supported on the Basic Plan and higher.
     # currencies : [optional] Specify a comma-separated list of currency codes to limit your API response to specific currencies.


  Scenario: Real time single source to single currency exchange rates
    Given my source currency is EUR
    And my exchange currency is USD
    When I lookup for the currency exchange rates
    Then currency exchange rate lookup is successful
    And I will have current timestamp
    And I will have the real time exchange rates


   Scenario: Real time single source to single currency exchange rates, using lowercase letters for currency code
     Given my source currency is eur, in lowercase
     And my exchange currency is USD
     When I lookup for the currency exchange rates
     Then currency exchange rate lookup is successful
     And I will have current timestamp
     And I will have the real time exchange rates


   Scenario: Real time single source to single currency exchange rates, using lowercase letters for currency code
     Given my source currency is EUR
     And my exchange currency is usd, in lowercase
     When I lookup for the currency exchange rates
     Then currency exchange rate lookup is successful
     And I will have current timestamp
     And I will have the real time exchange rates


   Scenario: Real time source currency is set to null
     Given my source currency is null
     And my exchange currency is USD
     When I lookup for the currency exchange rates
     Then currency exchange rate lookup is successful
     And I will have current timestamp
     And source currency is set to default USD
     And I will have the real time exchange rates


   Scenario: Real time exchange currency is set to null
     Given my source currency is EUR
     And my exchange currency is null
     When I lookup for the currency exchange rates
     Then currency exchange rate lookup is successful
     And I will have current timestamp
     And I will have the real time exchange rates


   Scenario: Real time source and exchange currency are both set to null
     Given my source currency is null
     And my exchange currency is null
     When I lookup for the currency exchange rates
     Then currency exchange rate lookup is successful
     And I will have current timestamp
     And source currency is set to default USD
     And I will have the real time exchange rates


   Scenario: Real time single source to multiple currencies exchange rates
     Given my source currency is EUR
     When I lookup for EUR to multiple currencies exchange rates
       |Exchange Currency |
       |USD               |
       |JPY               |
       |GBP               |
       |AUD               |
       |CAD               |
       |CHF               |
       |CNY               |
       |NZD               |
     Then currency exchange rate lookup is successful
     And I will have current timestamp
     And I will have the real time exchange rates


   Scenario: Real time multiple source currencies to single currency exchange rates
     Given my source currencies are 8 major currencies:
       |Source Currency   |
       |USD               |
       |JPY               |
       |GBP               |
       |AUD               |
       |CAD               |
       |CHF               |
       |CNY               |
       |NZD               |
     And my exchange currency is EUR
     When I lookup for the currency exchange rates
     Then currency exchange rate lookup is unsuccessful
     And I will not have current timestamp
     And I will not have the real time exchange rates


   Scenario: Real time multiple source currencies to multiple currencies exchange rates
     Given I have 8 source currencies and 8 exchange currencies:
       |Source Currency   |Exchange Currency  |
       |USD               |EUR                |
       |JPY               |CNY                |
       |GBP               |CAD                |
       |AUD               |GBP                |
       |CAD               |NZD                |
       |CHF               |EUR                |
       |CNY               |JPY                |
       |NZD               |AUD                |
     When I lookup for the currency exchange rates
     Then currency exchange rate lookup is unsuccessful
     And I will not have current timestamp
     And I will not have the real time exchange rates


   Scenario: Source currency code is not standard 3 letter code
     Given my source currency is EURO
     And my exchange currency is USD
     When I lookup for the currency exchange rates
     Then currency exchange rate lookup is unsuccessful
     And I will not have current timestamp
     And I will not have the real time exchange rates


   Scenario: Exchange currency code is not standard 3 letter code
     Given my source currency is EUR
     And my exchange currency is DOLLAR
     When I lookup for the currency exchange rates
     Then currency exchange rate lookup is unsuccessful
     And I will not have current timestamp
     And I will not have the real time exchange rates


   Scenario: Source and exchange currency codes are not standard 3 letter code
     Given my source currency is EURO
     And my exchange currency is DOLLAR
     When I lookup for the currency exchange rates
     Then currency exchange rate lookup is unsuccessful
     And I will not have current timestamp
     And I will not have the real time exchange rates


   Scenario: Real time single source to single currency exchange rates with invalid access key
     Given my source currency is EUR
     And my exchange currency is USD
     And my access key is invalid
     When I lookup for the currency exchange rates
     Then currency exchange rate lookup is unsuccessful
     And I will not have current timestamp
     And I will not have the real time exchange rates