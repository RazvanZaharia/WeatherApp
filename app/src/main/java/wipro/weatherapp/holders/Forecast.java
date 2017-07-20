package wipro.weatherapp.holders;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Forecast implements Serializable {
    private static final long serialVersionUID = -5522538748526567164L;

    @SerializedName("dt")
    private long mTimeOfForcast;
    @SerializedName("dt_txt")
    private String mDateOfCalculation;
    @SerializedName("main")
    private Temperature mTemperature;
    @SerializedName("weather")
    private List<Weather> mWeatherConditionsList;
    @SerializedName("clouds")
    private Clouds mCloudsInformaion;
    @SerializedName("wind")
    private Wind mWindInformation;
    @SerializedName("rain")
    private Rain mRainInformation;
    @SerializedName("snow")
    private Snow mSnowInformation;

    public long getTimeOfForcast() {
        return mTimeOfForcast;
    }

    public void setTimeOfForcast(long timeOfForcast) {
        mTimeOfForcast = timeOfForcast;
    }

    public String getDateOfCalculation() {
        return mDateOfCalculation;
    }

    public void setDateOfCalculation(String dateOfCalculation) {
        mDateOfCalculation = dateOfCalculation;
    }

    public Temperature getTemperature() {
        return mTemperature;
    }

    public void setTemperature(Temperature temperature) {
        mTemperature = temperature;
    }

    public List<Weather> getWeatherConditionsList() {
        return mWeatherConditionsList;
    }

    public void setWeatherConditionsList(List<Weather> weatherConditionsList) {
        mWeatherConditionsList = weatherConditionsList;
    }

    public Clouds getCloudsInformaion() {
        return mCloudsInformaion;
    }

    public void setCloudsInformaion(Clouds cloudsInformaion) {
        mCloudsInformaion = cloudsInformaion;
    }

    public Wind getWindInformation() {
        return mWindInformation;
    }

    public void setWindInformation(Wind windInformation) {
        mWindInformation = windInformation;
    }

    public Rain getRainInformation() {
        return mRainInformation;
    }

    public void setRainInformation(Rain rainInformation) {
        mRainInformation = rainInformation;
    }

    public Snow getSnowInformation() {
        return mSnowInformation;
    }

    public void setSnowInformation(Snow snowInformation) {
        mSnowInformation = snowInformation;
    }
}
