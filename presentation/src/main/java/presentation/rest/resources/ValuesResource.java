package presentation.rest.resources;

import org.joda.time.DateTime;

import java.util.List;
public class ValuesResource extends BaseResource {

    private DateTime dateTime;

    private String dateLabel;

    private List<Integer> values;

    public DateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(DateTime dateTime) {
        this.dateTime = dateTime;
        this.dateLabel = formatTime(dateTime);
    }

    public String getDateLabel() {
        return dateLabel;
    }

    public List<Integer> getValues() {
        return values;
    }

    public void setValues(List<Integer> values) {
        this.values = values;
    }

    @Override
    public String toString() {
        return String.format("Value1:%s; Value2:%s; Generated at: %s",
                values.get(0),
                values.get(1),
                formatDate(dateTime));
    }
}
