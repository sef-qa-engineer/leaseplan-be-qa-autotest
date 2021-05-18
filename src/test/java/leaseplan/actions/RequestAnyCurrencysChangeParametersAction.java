package leaseplan.actions;

import io.restassured.response.Response;
import leaseplan.BaseTest;
import leaseplan.CurrencyUtility;
import static io.restassured.RestAssured.given;

public class RequestAnyCurrencysChangeParametersAction extends BaseTest
{

    private CurrencyUtility utility = new CurrencyUtility();
    private String accessKey = "";
    private String isSuccessful = "";
    private String isChange = "";
    private String source = "";
    private String responseCurrencyMarginAndPerctageChanges = "";

    public void lookUpCurrencyExchangeChanges(String sourceCurrency, String exchangeCurrency, String accessKey, String startDate, String endDate)
    {
        if(accessKey.isEmpty()) { accessKey = access_key; }

        Response response = given()
                .baseUri(protocol + base_url)
                .basePath(base_changepath)
                .queryParams("access_key", accessKey)
                .queryParams("startDate", startDate)
                .queryParams("endDate", endDate)
                .queryParams("source", sourceCurrency)
                .queryParams("currencies", exchangeCurrency)
                .when()
                .get();

        testlog += response.asString();

        isSuccessful = response.jsonPath().getString("success");
        if(response.getStatusCode() == 200) {
            isChange = response.jsonPath().getString("change");
            source = response.jsonPath().getString("source");
            responseCurrencyMarginAndPerctageChanges = response.jsonPath().getString("quotes");
        }

    }

    public String isSuccessful()
    {
        return isSuccessful;
    }

    public String isHistorical()
    {
        return isChange;
    }

    public String responseSourceCurrency()
    {
        return source;
    }

    public String responseCurrencyMarginAndPerctageChanges()
    {
        return responseCurrencyMarginAndPerctageChanges;
    }

    public void writeTestLogsToFile(String stepDefName)
    {
        utility.printToTextFile(stepDefName,testlog);
    }

}
