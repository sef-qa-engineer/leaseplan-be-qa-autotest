package leaseplan;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CustomDate {

    private LocalDate startDate;
    private LocalDate endDate;
    private DateTimeFormatter format;

    public CustomDate()
    {
        this.startDate = null;
        this.endDate = null;
        this.format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    }

    public void setStartDate(LocalDate startDate)
    {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate)
    {
        this.endDate = endDate;
    }

    public String getStartDate()
    {
        return this.startDate.format(this.format);
    }

    public String getEndDate()
    {
        return this.endDate.format(this.format);
    }

    public LocalDate getDateToday()
    {
        return LocalDate.now();
    }

    public LocalDate getEarlierDate(int days)
    {
        LocalDate now = LocalDate.now();
        LocalDate then = now.minusDays(days);
        return then;
    }

    public LocalDate getFutureDate(int days)
    {
        LocalDate now = LocalDate.now();
        LocalDate future = now.plusDays(days);
        return future;
    }

}
