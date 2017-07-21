package wipro.weatherapp.utils;


import android.content.Context;
import android.support.annotation.NonNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Utils {

    public static int getDrawableIdentifierByServerCode(@NonNull Context ctx, @NonNull String code) {
        return ctx.getResources().getIdentifier("w_" + code, "drawable", ctx.getPackageName());
    }

    public static String formatDateToDayMonthYear(@NonNull String currentDate) {
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

    public static String formatDateToHour(@NonNull String currentDate) {
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

    public static String formatDateMonthDay(@NonNull String currentDate) {
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

    public static String formatDateMonthFullDayName(@NonNull String currentDate) {
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
