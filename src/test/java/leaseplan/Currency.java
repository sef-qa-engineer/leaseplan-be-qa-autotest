package leaseplan;

public class Currency
{
    private String sourceCurrency;
    private String exchangeCurrency;
    private String accessKey;
    private String date;

    public Currency()
    {
        this.sourceCurrency = "";
        this.exchangeCurrency = "";
        this.accessKey = "";
        this.date = "";
    }

    public void setSourceCurrency(String baseCurrency)
    {
        this.sourceCurrency = baseCurrency;
    }

    public void setExchangeCurrency(String exchangeCurrency)
    {
        this.exchangeCurrency = exchangeCurrency;
    }

    public void setAccessKey(String accessKey)
    {
        this.accessKey = accessKey;
    }

    public void setDate(String date)
    {
        this.date = date;
    }

    public String getSourceCurrency()
    {
        return this.sourceCurrency;
    }

    public String getExchangeCurrency()
    {
        return this.exchangeCurrency;
    }

    public String getAccessKey()
    {
        return this.accessKey;
    }

    public String getDate()
    {
        return this.date;
    }

    public void addSourceCurrency(String sourceCurrency)
    {
        this.sourceCurrency = this.sourceCurrency + sourceCurrency + ",";
    }

    public void addExchangeCurrency(String exchangeCurrency)
    {
        this.exchangeCurrency = this.exchangeCurrency + exchangeCurrency + ",";
    }

}
