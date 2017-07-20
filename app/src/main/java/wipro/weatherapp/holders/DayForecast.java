package wipro.weatherapp.holders;


import java.io.Serializable;
import java.util.List;

public class DayForecast implements Serializable {
    private static final long serialVersionUID = 4227761885490983289L;

    private String mDayDate;
    private List<Forecast> mForecastList;

    public String getDayDate() {
        return mDayDate;
    }

    public void setDayDate(String dayDate) {
        mDayDate = dayDate;
    }

    public List<Forecast> getForecastList() {
        return mForecastList;
    }

    public void setForecastList(List<Forecast> forecastList) {
        mForecastList = forecastList;
    }
}
