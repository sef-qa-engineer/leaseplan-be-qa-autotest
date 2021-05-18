Feature: Request exchange rates for a specific period of time
  Background: Timeframe is defined by start and end dates
     # Endpoint: https://api.currencylayer.com/timeframe
     # access_key : [Required] Your API Access Key.
     # start_date : [Required] Specify the start date of your time frame.
     # end_date : [Required] Specify the end date of your time frame.
     # source : [optional] Specify a Source Currency other than the default USD.
     # currencies : [optional] Specify a comma-separated list of currency codes to limit your API response to specific currencies.

  Scenario: Request exchange rates for a a period of 4 days
    Given my exchange currencies are for timeframe lookup
    |Exchange Currencies  |
    |EUR                  |
    |GBP                  |
    |CAD                  |
    |CHF                  |
    |JPY                  |
    And my start date is 5 days ago
    And my end date is yesterday
    When I lookup for the currency exchange rates that timeframe
    Then currency exchange rate for specific timeframe lookup is successful
    And I will get currency exchange rate or timeframe

  Scenario: Request exchange rates for a a period of 4 days - invalid timeframe
    Given my exchange currencies are for timeframe lookup
      |Exchange Currencies  |
      |EUR                  |
      |GBP                  |
      |CAD                  |
      |CHF                  |
      |JPY                  |
    And my start date is yesterday
    And my end date is 5 days ago
    When I lookup for the currency exchange rates that timeframe
    Then currency exchange rate for specific timeframe lookup is unsuccessful
    And I will not get currency exchange rate or timeframe