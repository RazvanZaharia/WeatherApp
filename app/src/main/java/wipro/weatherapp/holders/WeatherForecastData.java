package wipro.weatherapp.holders;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;


public class WeatherForecastData implements Serializable {
    private static final long serialVersionUID = 3932347799041563834L;

    @SerializedName("cod")
    private String mApiCode;
    @SerializedName("list")
    private List<Forecast> mForecastList;
    @SerializedName("city")
    private City mCity;

    public String getApiCode() {
        return mApiCode;
    }

    public void setApiCode(String apiCode) {
        mApiCode = apiCode;
    }

    public List<Forecast> getForecastList() {
        return mForecastList;
    }

    public void setForecastList(List<Forecast> forecastList) {
        mForecastList = forecastList;
    }

    public City getCity() {
        return mCity;
    }

    public void setCity(City city) {
        mCity = city;
    }
}
