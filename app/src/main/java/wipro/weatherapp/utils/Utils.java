package wipro.weatherapp.utils;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Utils {

    public static String formatDateToDayMonthYear(String currentDate) {
        String formattedDate = null;
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.getDefault());
            Date newDate = format.parse(currentDate);

            format = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
            formattedDate = format.format(newDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return formattedDate;
    }

    public static String formatDateToHour(String currentDate) {
        String formattedDate = null;
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.getDefault());
            Date newDate = format.parse(currentDate);

            format = new SimpleDateFormat("HH:mm", Locale.getDefault());
            formattedDate = format.format(newDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return formattedDate;
    }

    public static String formatDateMonthDay(String currentDate) {
        String formattedDate = null;
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.getDefault());
            Date newDate = format.parse(currentDate);

            format = new SimpleDateFormat("MM dd", Locale.getDefault());
            formattedDate = format.format(newDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return formattedDate;
    }

    public static String formatDateMonthFullDayName(String currentDate) {
        String formattedDate = null;
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
            Date newDate = format.parse(currentDate);

            format = new SimpleDateFormat("EEEE dd MMM ", Locale.getDefault());
            formattedDate = format.format(newDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return formattedDate;
    }

}
