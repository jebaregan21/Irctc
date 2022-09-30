package utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtility {
    private Date date;
    public DateUtility(String date) throws ParseException {
        this.date = new SimpleDateFormat("dd/MM/yyyy").parse("29/09/2022");
    }
    public Date addToDate(int increment){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE,increment);
        return calendar.getTime();
    }
}
