package leaseplan.stepdefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import leaseplan.Currency;
import leaseplan.CustomDate;
import leaseplan.actions.RequestExchangeRatesForASpecificPeriodOfTimeAction;
import org.junit.Assert;

import java.util.List;
import java.util.Map;

public class RequestExchangeRatesForASpecificPeriodOfTime
{
    private Currency currency = new Currency();
    private CustomDate date = new CustomDate();
    private RequestExchangeRatesForASpecificPeriodOfTimeAction action = new RequestExchangeRatesForASpecificPeriodOfTimeAction();

    @Before
    public void setUpTestProperties() {
        action.setupTestDataSet();
    }

    @After
    public void writeTestLogs() {
        action.writeTestLogsToFile("RequestExchangeRatesForASpecificPeriodOfTime");
    }

    @Given("my exchange currencies are for timeframe lookup")
    public void myExchangeCurenciesAre(DataTable table)
    {
        List<Map<String, String>> rows = table.asMaps(String.class, String.class);

        for (Map<String, String> columns : rows) {
            currency.addExchangeCurrency(columns.get("Exchange Currency"));
        }
    }

    @Given("my start date is 5 days ago")
    public void myStartDateIs_5_DaysAgo()
    {
        date.setStartDate(date.getEarlierDate(5));
    }

    @Given("my end date is yesterday")
    public void myEndDateIsYesterday()
    {
        date.setEndDate(date.getEarlierDate(1));
    }

    @Given("my start date is yesterday")
    public void myStartDateIsYesterday()
    {
        date.setStartDate(date.getEarlierDate(1));
    }

    @Given("my end date is 5 days ago")
    public void myEndDateIs_5_DaysAgo()
    {
        date.setEndDate(date.getEarlierDate(5));
    }

    @When("I lookup for the currency exchange rates that timeframe")
    public void iLookupForTheCurrencyExchangeRatesThatTimeframe()
    {
        action.lookUpCurrencyExchangeRateTimeframe(currency.getExchangeCurrency(),currency.getAccessKey(),date.getStartDate(),date.getEndDate());
    }

    @Then("currency exchange rate for specific timeframe lookup is successful")
    public void currencyExchangeRateForSpecificTimeframeLookupIsSuccessful()
    {
        Assert.assertEquals("true",action.isSuccessful());
    }

    @Then("I will get currency exchange rate or timeframe")
    public void iWillGetCurrencyExchangeRateOrTimeframe()
    {
        Assert.assertNotNull(action.responseCurrencyRateForTimeframe());
    }


    @Then("currency exchange rate for specific timeframe lookup is unsuccessful")
    public void currencyExchangeRateForSpecificTimeframeLookupIsUnsuccessful()
    {
        Assert.assertEquals("false",action.isSuccessful());
    }

    @And("I will not get currency exchange rate or timeframe")
    public void iWillNotGetCurrencyExchangeRateOrTimeframe()
    {
        Assert.assertNull(action.responseCurrencyRateForTimeframe());
    }
}
