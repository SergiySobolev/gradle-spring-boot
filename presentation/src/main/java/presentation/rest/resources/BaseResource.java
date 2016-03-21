package presentation.rest.resources;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.springframework.hateoas.ResourceSupport;

public class BaseResource extends ResourceSupport {

    public String formatDate(DateTime dateTime){
        String PATTERN = "MM/dd/yyyy HH:mm:ss";
        return DateTimeFormat.forPattern(PATTERN).print(dateTime);
    }

    public String formatTime(DateTime dateTime) {
        String PATTERN = "HH:mm:ss";
        return DateTimeFormat.forPattern(PATTERN).print(dateTime);
    }
}
