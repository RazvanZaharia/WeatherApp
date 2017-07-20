package wipro.weatherapp.holders;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Coordinates implements Serializable {
    private static final long serialVersionUID = 3132958216393617506L;

    @SerializedName("lon")
    private float mLongitude;
    @SerializedName("lat")
    private float mLatitude;

    public float getLongitude() {
        return mLongitude;
    }

    public void setLongitude(float longitude) {
        mLongitude = longitude;
    }

    public float getLatitude() {
        return mLatitude;
    }

    public void setLatitude(float latitude) {
        mLatitude = latitude;
    }
}
