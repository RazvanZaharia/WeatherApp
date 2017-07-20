package wipro.weatherapp.holders;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Clouds implements Serializable {
    private static final long serialVersionUID = 4349669821332480320L;

    @SerializedName("all")
    private float mCloudinessPercent;

    public float getCloudinessPercent() {
        return mCloudinessPercent;
    }

    public void setCloudinessPercent(float cloudinessPercent) {
        mCloudinessPercent = cloudinessPercent;
    }
}
