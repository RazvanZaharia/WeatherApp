package wipro.weatherapp.holders;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Temperature implements Serializable {
    private static final long serialVersionUID = 8297784108980079068L;

    @SerializedName("temp")
    private float mCurrentTemperature;
    @SerializedName("temp_min")
    private float mMinimumTemperature;
    @SerializedName("temp_max")
    private float mMaximumTemperature;

    public int getCurrentTemperature() {
        return (int)mCurrentTemperature;
    }

    public void setCurrentTemperature(float currentTemperature) {
        mCurrentTemperature = currentTemperature;
    }

    public int getMinimumTemperature() {
        return (int)mMinimumTemperature;
    }

    public void setMinimumTemperature(float minimumTemperature) {
        mMinimumTemperature = minimumTemperature;
    }

    public int getMaximumTemperature() {
        return (int)mMaximumTemperature;
    }

    public void setMaximumTemperature(float maximumTemperature) {
        mMaximumTemperature = maximumTemperature;
    }
}
