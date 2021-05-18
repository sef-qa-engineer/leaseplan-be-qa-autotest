package leaseplan.actions;

import io.restassured.response.Response;
import leaseplan.BaseTest;
import leaseplan.CurrencyUtility;
import static io.restassured.RestAssured.given;
import static leaseplan.CurrencyUtility.printToTextFile;

public class RealTimeCurrencyExchangeRatesAction extends BaseTest
{
    private CurrencyUtility utility = new CurrencyUtility();
    private String accesKey = "";
    private String isSuccessful = "";
    private String timeStamp = "";
    private String source = "";
    private String exchangeRates = "";

    public void lookUpExchangeRates(String sourceCurrency, String exchangeCurrency, String accessKey)
    {
        if(accessKey.isEmpty()) { accessKey = access_key; }

        Response response = given()
                            .baseUri(protocol + base_url)
                            .basePath(base_livepath)
                            .queryParams("access_key", accessKey)
                            .queryParams("source", sourceCurrency)
                            .queryParams("currencies", exchangeCurrency)
                            .queryParams("format", "1")
                            .when()
                            .get();

        testlog += response.asString();

        isSuccessful = response.jsonPath().getString("success");
        if(response.getStatusCode() == 200) {
            timeStamp = response.jsonPath().getString("timestamp");
            source = response.jsonPath().getString("source");
            exchangeRates = response.jsonPath().getString("quotes");
        }

    }

    public String isSuccessful()
    {
        return isSuccessful;
    }

    public String responseTimeStamp()
    {
        return timeStamp;
    }

    public String responseSourceCurrency()
    {
        return source;
    }

    public String responseCurrencyExchangeRates()
    {
        return exchangeRates;
    }

    public void writeTestLogsToFile(String stepDefName)
    {
        //utility.printToTextFile(stepDefName,testlog);
    }


}
