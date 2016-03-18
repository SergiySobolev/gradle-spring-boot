package presentation.rest.resources;

import org.joda.time.DateTime;

import java.util.List;
public class ValuesResource extends BaseResource {

    private String company = "Test";
    private DateTime dateTime;
    private List<Integer> values;


    public DateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(DateTime dateTime) {
        this.dateTime = dateTime;
    }

    public List<Integer> getValues() {
        return values;
    }

    public void setValues(List<Integer> values) {
        this.values = values;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return String.format("Value1:%s; Value2:%s; Generated at: %s",
                values.get(0),
                values.get(1),
                formatDate(dateTime));
    }
}
