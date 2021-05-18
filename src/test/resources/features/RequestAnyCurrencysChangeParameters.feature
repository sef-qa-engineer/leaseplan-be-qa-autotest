Feature: Request any currency's change parameters
  Background: Request any currency's change parameters, such as margin and percentage changes
     # Endpoint: https://api.currencylayer.com/change
     # access_key : [Required] Your API Access Key.
     # start_date : [Required] Specify the start date of your time frame.
     # end_date : [Required] Specify the end date of your time frame.
     # source : [optional] Specify a Source Currency other than the default USD.
     # currencies : [optional] Specify a comma-separated list of currency codes to limit your API response to specific currencies.

  Scenario: Request margin and percentage changes between 2 currencies within a specified date range
    Given my change source currency is EUR
    And my change exchange currency is USD
    And end date is today
    And start date is 5 days earlier than today
    When I lookup for the currency exchange rate changes
    Then currency exchange rate changes rate changes lookup is successful
    And I will get margin and percentage changes


  Scenario: Request margin and percentage changes between a specified source currency compared to 5 currencies within a specified date range
    Given my change source currency is EUR
    And end date is today
    And start date is 5 days earlier than today
    And my exchange currencies are
    |Exchange Currency |
    |USD               |
    |GBP               |
    |CHF               |
    |JPY               |
    |CAD               |
    When I lookup for the currency exchange rate changes
    Then currency exchange rate changes rate changes lookup is successful
    And I will get 5 change trends


  Scenario: Request margin and percentage changes between a default source currency compared to 5 currencies within a specified date range
    Given my change source currency is null
    And end date is today
    And start date is 5 days earlier than today
    And my exchange currencies are
      |Exchange Currency |
      |USD               |
      |GBP               |
      |CHF               |
      |JPY               |
      |CAD               |
    When I lookup for the currency exchange rate changes
    Then currency exchange rate changes rate changes lookup is successful
    And I will get USD margin and percentage change for 5 currencies


  Scenario: Request margin and percentage changes between 2 currencies within a specified date range but start date is today and end date is earlier
    Given my change source currency is EUR
    And my change exchange currency is USD
    And start date is today
    And end date is 5 days earlier than today
    When I lookup for the currency exchange rate changes
    Then currency exchange rate changes rate changes lookup is unsuccessful
    And I will not get margin and percentage changes


  Scenario: Request margin and percentage changes between 2 currencies within a specified date range but start date is today and end date is future date
    Given my change source currency is EUR
    And my change exchange currency is USD
    And start date is today
    And end date is 5 days from today
    When I lookup for the currency exchange rate changes
    Then currency exchange rate changes rate changes lookup is unsuccessful
    And I will not get margin and percentage changes