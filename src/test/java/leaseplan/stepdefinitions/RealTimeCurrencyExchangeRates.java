package leaseplan.stepdefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.util.List;
import java.util.Map;
import leaseplan.Currency;
import leaseplan.actions.RealTimeCurrencyExchangeRatesAction;
import org.jetbrains.annotations.NotNull;
import org.junit.Assert;


public class RealTimeCurrencyExchangeRates
{
    private Currency currency = new Currency();
    private RealTimeCurrencyExchangeRatesAction action = new RealTimeCurrencyExchangeRatesAction();

    @Before
    public void setUpTestProperties()
    {
        action.setupTestDataSet();
    }

    @After
    public void writeTestLogs()
    {
        action.writeTestLogsToFile("RealTimeCurrencyExchangeRates");
    }

    @Given("my source currency is EUR")
    public void mySourceCurrencyIsEUR()
    {
        currency.setSourceCurrency("EUR");
    }

    @Given("my exchange currency is USD")
    public void myExchangeCurrencyIsUSD()
    {
        currency.setExchangeCurrency("USD");
    }

    @Given("my source currency is eur, in lowercase")
    public void mySourceCurrencyIsEurInLowercase() {
        currency.setSourceCurrency("eur");
    }

    @Given("my exchange currency is usd, in lowercase")
    public void myExchangeCurrencyIsUsdInLowercase() {
        currency.setExchangeCurrency("usd");
    }

    @Given("my source currencies are 8 major currencies:")
    public void mySourceCurrenciesAre8MajorCurrencies(@NotNull DataTable table)
    {
        List<Map<String,String>> rows = table.asMaps(String.class,String.class);

        for (Map<String,String> columns : rows) {
            currency.addSourceCurrency(columns.get("Source Currency"));
        }
    }

    @Given("my exchange currency is EUR")
    public void myExchangeCurrencyIsEUR()
    {
        currency.setExchangeCurrency("EUR");
    }

    @Given("I have 8 source currencies and 8 exchange currencies:")
    public void iHave8SourceCurrenciesAnd8ExchangeCurrencies(@NotNull DataTable table)
    {
        List<Map<String,String>> rows = table.asMaps(String.class,String.class);

        for (Map<String,String> columns : rows) {
            currency.addSourceCurrency(columns.get("Source Currency"));
            currency.addExchangeCurrency(columns.get("Exchange Currency"));
        }

    }

    @Given("my source currency is EURO")
    public void mySourceCurrencyIsEURO()
    {
        currency.setSourceCurrency("EURO");
    }

    @Given("my exchange currency is DOLLAR")
    public void myExchangeCurrencyIsDOLLAR()
    {
        currency.setExchangeCurrency("DOLLAR");
    }

    @Given("my source currency is null")
    public void mySourceCurrencyIsNull() {
        currency.setSourceCurrency("");
    }

    @Given("my exchange currency is null")
    public void myExchangeCurrencyIsNull() {
        currency.setExchangeCurrency("");
    }

    @Given("my access key is invalid")
    public void myAccessKeyIsInvalid()
    {
        currency.setAccessKey("invalid");
    }

    @When("I lookup for the currency exchange rates")
    public void iLookupForTheCurrencyExchangeRates()
    {
        action.lookUpExchangeRates(currency.getSourceCurrency(),currency.getExchangeCurrency(),currency.getAccessKey());
    }

    @When("I lookup for EUR to multiple currencies exchange rates")
    public void iLookupFor_EUR_ToMultipleCurrenciesExchangeRates(@NotNull DataTable table)
    {
        List<Map<String,String>> rows = table.asMaps(String.class,String.class);

        for (Map<String,String> columns : rows) {
            currency.addExchangeCurrency(columns.get("Exchange Currency"));
        }

        action.lookUpExchangeRates(currency.getSourceCurrency(),currency.getExchangeCurrency(),currency.getAccessKey());
    }

    @Then("currency exchange rate lookup is successful")
    public void currencyExchangeRateLookupIsSuccessful()
    {
        Assert.assertEquals("true",action.isSuccessful());
    }

    @Then("I will have current timestamp")
    public void iWillHaveCurrentTimestamp()
    {
        Assert.assertNotNull(action.responseTimeStamp());
    }

    @Then("I will have the real time exchange rates")
    public void iWillHaveTheRealTimeExchangeRates()
    {
        Assert.assertNotNull(action.responseCurrencyExchangeRates());
    }

    @Then("currency exchange rate lookup is unsuccessful")
    public void currencyExchangeRateLookupIsUnsuccessful()
    {
        Assert.assertEquals("false",action.isSuccessful());
    }

    @Then("I will not have current timestamp")
    public void iWillNotHaveCurrentTimestamp()
    {
        Assert.assertNull(action.responseTimeStamp());
    }

    @Then("I will not have the real time exchange rates")
    public void iWillNotHaveTheRealTimeExchangeRates()
    {
        Assert.assertNull(action.responseCurrencyExchangeRates());
    }

    @Then("source currency is set to default USD")
    public void sourceCurrencyIsSetToDefaultUSD()
    {
        Assert.assertEquals("USD",action.responseSourceCurrency());
    }


}
