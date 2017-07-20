package wipro.weatherapp.holders;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class Snow implements Serializable {
    private static final long serialVersionUID = 4646336974231072954L;

    @SerializedName("3h")
    private float mSnowVolumeLast3h;

    public float getSnowVolumeLast3h() {
        return mSnowVolumeLast3h;
    }

    public void setSnowVolumeLast3h(float snowVolumeLast3h) {
        mSnowVolumeLast3h = snowVolumeLast3h;
    }
}
