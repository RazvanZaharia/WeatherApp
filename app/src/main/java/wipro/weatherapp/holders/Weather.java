package wipro.weatherapp.holders;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Weather implements Serializable {
    private static final long serialVersionUID = 1302068995381141656L;

    @SerializedName("id")
    private int mId;
    @SerializedName("main")
    private String mMain;
    @SerializedName("description")
    private String mDescription;
    @SerializedName("icon")
    private String mIconId;

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getMain() {
        return mMain;
    }

    public void setMain(String main) {
        mMain = main;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public String getIconId() {
        return mIconId;
    }

    public void setIconId(String iconId) {
        mIconId = iconId;
    }
}
