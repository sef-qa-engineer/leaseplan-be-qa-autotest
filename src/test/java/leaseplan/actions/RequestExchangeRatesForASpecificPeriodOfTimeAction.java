package leaseplan.actions;

import io.restassured.response.Response;
import leaseplan.BaseTest;
import leaseplan.CurrencyUtility;

import static io.restassured.RestAssured.given;

public class RequestExchangeRatesForASpecificPeriodOfTimeAction extends BaseTest
{
    private CurrencyUtility utility = new CurrencyUtility();
    private String accessKey = "";
    private String isSuccessful = "";
    private String responseCurrencyRateForTimeframe = "";

    public void lookUpCurrencyExchangeRateTimeframe(String exchangeCurrency, String accessKey, String startDate, String endDate)
    {
        if(accessKey.isEmpty()) { accessKey = access_key; }

        Response response = given()
                .baseUri(protocol + base_url)
                .basePath(base_timeframepath)
                .queryParams("access_key", accessKey)
                .queryParams("startDate", startDate)
                .queryParams("endDate", endDate)
                .queryParams("currencies", exchangeCurrency)
                .when()
                .get();

        testlog += response.asString();

        isSuccessful = response.jsonPath().getString("success");
        if(response.getStatusCode() == 200) {
            responseCurrencyRateForTimeframe = response.jsonPath().getString("quotes");
        }

    }

    public String isSuccessful()
    {
        return isSuccessful;
    }

    public String responseCurrencyRateForTimeframe()
    {
        return responseCurrencyRateForTimeframe;
    }

    public void writeTestLogsToFile(String stepDefName)
    {
        utility.printToTextFile(stepDefName,testlog);
    }

}
