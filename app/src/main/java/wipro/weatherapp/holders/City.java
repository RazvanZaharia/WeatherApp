package wipro.weatherapp.holders;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class City implements Serializable {
    private static final long serialVersionUID = 8323089482594869777L;

    @SerializedName("id")
    private long mId;
    @SerializedName("name")
    private String mName;
    @SerializedName("coord")
    private Coordinates mCoordinates;
    @SerializedName("country")
    private String mCountryCode;

    public City() {
    }

    public City(String name) {
        this.mName = name;
    }

    public long getId() {
        return mId;
    }

    public void setId(long id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public Coordinates getCoordinates() {
        return mCoordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        mCoordinates = coordinates;
    }

    public String getCountryCode() {
        return mCountryCode;
    }

    public void setCountryCode(String countryCode) {
        mCountryCode = countryCode;
    }
}
