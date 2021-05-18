package leaseplan.stepdefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import leaseplan.Currency;
import leaseplan.actions.HistoricalCurrencyExchangeRatesAction;
import org.jetbrains.annotations.NotNull;
import org.junit.Assert;
import java.util.List;
import java.util.Map;


public class HistoricalCurrencyExchangeRates
{
    private Currency currency = new Currency();
    private HistoricalCurrencyExchangeRatesAction action = new HistoricalCurrencyExchangeRatesAction();

    @Before
    public void setUpTestProperties()
    {
        action.setupTestDataSet();
    }

    @After
    public void writeTestLogs()
    {
        action.writeTestLogsToFile("HistoricalCurrencyExchangeRates");
    }

    @Given("my historical source currency is EUR")
    public void myHistoricalSourceCurrencyIsEUR()
    {
        currency.setSourceCurrency("EUR");
    }

    @Given("my historical exchange currency is USD")
    public void myHistoricalExchangeCurrencyIsUSD()
    {
        currency.setExchangeCurrency("USD");
    }

    @Given("my historical source currency is eur, in lowercase")
    public void myHistoricalSourceCurrencyIsEurInLowercase() {
        currency.setSourceCurrency("eur");
    }

    @Given("my historical exchange currency is usd, in lowercase")
    public void myHistoricalExchangeCurrencyIsUsdInLowercase() {
        currency.setExchangeCurrency("usd");
    }

    @Given("my historical source currencies are 8 major currencies:")
    public void myHistoricalSourceCurrenciesAre8MajorCurrencies(@NotNull DataTable table)
    {
        List<Map<String,String>> rows = table.asMaps(String.class,String.class);

        for (Map<String,String> columns : rows) {
            currency.addSourceCurrency(columns.get("Source Currency"));
        }
    }

    @Given("my historical exchange currency is EUR")
    public void myHistoricalExchangeCurrencyIsEUR()
    {
        currency.setExchangeCurrency("EUR");
    }

    @Given("I have 8 source currencies and 8 exchange historical currencies:")
    public void iHave8SourceCurrenciesAnd8ExchangeHistoricalCurrencies(@NotNull DataTable table)
    {
        List<Map<String,String>> rows = table.asMaps(String.class,String.class);

        for (Map<String,String> columns : rows) {
            currency.addSourceCurrency(columns.get("Source Currency"));
            currency.addExchangeCurrency(columns.get("Exchange Currency"));
        }

    }

    @Given("my historical source currency is EURO")
    public void myHistoricalSourceCurrencyIsEURO()
    {
        currency.setSourceCurrency("EURO");
    }

    @Given("my historical exchange currency is DOLLAR")
    public void myHistoricalExchangeCurrencyIsDOLLAR()
    {
        currency.setExchangeCurrency("DOLLAR");
    }

    @Given("my historical source currency is null")
    public void myHistoricalSourceCurrencyIsNull()
    {
        currency.setSourceCurrency("");
    }

    @Given("my historical exchange currency is null")
    public void myHistoricalExchangeCurrencyIsNull()
    {
        currency.setExchangeCurrency("");
    }

    @Given("date is 2021-05-01")
    public void date_is_2021_05_01()
    {
        currency.setDate("2021-05-01");
    }

    @Given("my historical access key is invalid")
    public void myHistoricalAccessKeyIsInvalid()
    {
        currency.setAccessKey("invalid");
    }

    @Given("date format is invalid")
    public void dateFormatIsInvalid()
    {
        currency.setDate("05-01-2021");
    }

    @When("I lookup for historical currency exchange rates")
    public void iLookupForHistoricalCurrencyExchangeRates()
    {
        action.lookUpExchangeRates(currency.getSourceCurrency(),currency.getExchangeCurrency(),currency.getDate(),currency.getAccessKey());
    }

    @When("I lookup for EUR to multiple currencies historical exchange rates")
    public void iLookupFor_EUR_ToMultipleCurrenciesHistoricalExchangeRates(@NotNull DataTable table)
    {
        List<Map<String,String>> rows = table.asMaps(String.class,String.class);

        for (Map<String,String> columns : rows) {
            currency.addExchangeCurrency(columns.get("Exchange Currency"));
        }

        action.lookUpExchangeRates(currency.getSourceCurrency(),currency.getExchangeCurrency(),currency.getDate(),currency.getAccessKey());
    }

    @Then("historical currency exchange rate lookup is successful")
    public void historicalCurrencyExchangeRateLookupIsSuccessful()
    {
        Assert.assertEquals("true",action.isSuccessful());
    }

    @Then("I will have historical timestamp")
    public void iWillHaveHistoricalTimestamp()
    {
        Assert.assertNotNull(action.responseTimeStamp());
    }

    @Then("historical exchange rate is true")
    public void historicalExchangeRateIsTrue()
    {
        Assert.assertEquals("true",action.isHistorical());
    }

    @Then("historical source currency is set to default USD")
    public void historicalSourceCurrencyIsSetToDefault_USD()
    {
        Assert.assertEquals("USD",action.responseSourceCurrency());
    }

    @Then("I will have the historical exchange rates")
    public void iWillHaveTheHistoricalExchangeRates()
    {
        Assert.assertNotNull(action.responseCurrencyExchangeRates());
    }

    @Then("I will not have the historical exchange rates")
    public void iWillNotHaveTheHistoricalExchangeRates()
    {
        Assert.assertNull(action.responseCurrencyExchangeRates());
    }

    @Then("historical currency exchange rate lookup is unsuccessful")
    public void historicalCurrencyExchangeRateLookupIsUnsuccessful()
    {
        Assert.assertEquals("false",action.isSuccessful());
    }

    @Then("I will not have historical timestamp")
    public void iWillNotHaveHistoricalTimestamp()
    {
        Assert.assertNull(action.responseCurrencyExchangeRates());
    }

}
