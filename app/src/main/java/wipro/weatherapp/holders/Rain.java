package wipro.weatherapp.holders;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class Rain implements Serializable {
    private static final long serialVersionUID = -4489036347980559625L;

    @SerializedName("3h")
    private float mRainVolumeLast3h;

    public float getRainVolumeLast3h() {
        return mRainVolumeLast3h;
    }

    public void setRainVolumeLast3h(float rainVolumeLast3h) {
        mRainVolumeLast3h = rainVolumeLast3h;
    }
}
