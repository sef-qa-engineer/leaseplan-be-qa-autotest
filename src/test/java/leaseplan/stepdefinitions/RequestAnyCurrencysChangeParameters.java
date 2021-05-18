package leaseplan.stepdefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import leaseplan.Currency;
import leaseplan.CustomDate;
import leaseplan.actions.RequestAnyCurrencysChangeParametersAction;
import org.jetbrains.annotations.NotNull;
import org.junit.Assert;
import java.util.List;
import java.util.Map;

public class RequestAnyCurrencysChangeParameters
{
    private Currency currency = new Currency();
    private CustomDate date = new CustomDate();
    private RequestAnyCurrencysChangeParametersAction action = new RequestAnyCurrencysChangeParametersAction();

    @Before
    public void setUpTestProperties() {
        action.setupTestDataSet();
    }

    @After
    public void writeTestLogs() {
        action.writeTestLogsToFile("RequestAnyCurrencysChangeParameters");
    }

    @Given("my change source currency is EUR")
    public void myChangeSourceCurrencyIs_EUR() {
        currency.setSourceCurrency("EUR");
    }

    @Given("my change exchange currency is USD")
    public void myChangeExchangeCurrencyIs_USD() {
        currency.setExchangeCurrency("USD");
    }

    @Given("end date is today")
    public void endDateIsToday() {
        date.setEndDate(date.getDateToday());
    }

    @Given("start date is 5 days earlier than today")
    public void startDateIs_5_DaysEarlierThanToday() {
        date.setStartDate(date.getEarlierDate(5));
    }

    @Given("my exchange currencies are")
    public void myExchangeCurrenciesAre(@NotNull DataTable table) {
        List<Map<String, String>> rows = table.asMaps(String.class, String.class);

        for (Map<String, String> columns : rows) {
            currency.addExchangeCurrency(columns.get("Exchange Currency"));
        }

    }

    @Given("my change source currency is null")
    public void myChangeSourceCurrencyIsNull() {
        currency.setSourceCurrency("");
    }

    @Given("start date is today")
    public void startDateIsToday()
    {
        date.setStartDate(date.getDateToday());
    }

    @Given("end date is 5 days earlier than today")
    public void endDateIs_5_DaysEarlierThanToday()
    {
        date.setEndDate(date.getEarlierDate(5));
    }

    @Given("end date is 5 days from today")
    public void endDateIs_5_DaysFromToday()
    {
        date.setEndDate(date.getFutureDate(5));
    }

    @When("I lookup for the currency exchange rate changes")
    public void iLookupForTheCurrencyExchangeRateChanges()
    {
        action.lookUpCurrencyExchangeChanges(currency.getSourceCurrency(),currency.getExchangeCurrency(),currency.getAccessKey(),date.getStartDate(),date.getEndDate());
    }

    @Then("currency exchange rate changes rate changes lookup is successful")
    public void currencyExchangeRateChangesRateChangesLookupIsSuccessful()
    {
        Assert.assertEquals("true",action.isSuccessful());
    }

    @Then("I will get margin and percentage changes")
    public void iWillGetMarginAndPercentageChanges()
    {
        Assert.assertNotNull(action.responseCurrencyMarginAndPerctageChanges());
    }

    @Then("I will get 5 change trends")
    public void iWillGet_5_ChangeTrends()
    {
        Assert.assertNotNull(action.responseCurrencyMarginAndPerctageChanges());
    }

    @Then("I will get USD margin and percentage change for 5 currencies")
    public void iWillGet_USD_MarginAndPercentageChangeFor_5_Currencies()
    {
        Assert.assertNotNull(action.responseCurrencyMarginAndPerctageChanges());
    }

    @Then("currency exchange rate changes rate changes lookup is unsuccessful")
    public void currencyExchangeRateChangesRateChangesLookupIsUnsuccessful()
    {
        Assert.assertEquals("false",action.isSuccessful());
    }

    @Then("I will not get margin and percentage changes")
    public void iWillNotGetMarginAndPercentageChanges()
    {
        Assert.assertNull(action.responseCurrencyMarginAndPerctageChanges());
    }

}
