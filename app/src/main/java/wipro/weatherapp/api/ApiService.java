package wipro.weatherapp.api;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import wipro.weatherapp.holders.WeatherForecastData;

public interface ApiService {
    static final String BASE_URL = "http://api.openweathermap.org/data/2.5/";

    @GET("forecast")
    Call<WeatherForecastData> getForecastByCityName(@Query("q") String cityName);

}
