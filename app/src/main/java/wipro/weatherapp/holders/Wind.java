package wipro.weatherapp.holders;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class Wind implements Serializable {
    private static final long serialVersionUID = -2011774519795889744L;

    @SerializedName("speed")
    private float mWindSpeed;
    @SerializedName("deg")
    private float mWindDirectionDegrees;

    public float getWindSpeed() {
        return mWindSpeed;
    }

    public void setWindSpeed(float windSpeed) {
        mWindSpeed = windSpeed;
    }

    public float getWindDirectionDegrees() {
        return mWindDirectionDegrees;
    }

    public void setWindDirectionDegrees(float windDirectionDegrees) {
        mWindDirectionDegrees = windDirectionDegrees;
    }
}
