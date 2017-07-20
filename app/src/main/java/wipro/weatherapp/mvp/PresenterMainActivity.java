package wipro.weatherapp.mvp;


import android.support.annotation.NonNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import wipro.weatherapp.api.RestClient;
import wipro.weatherapp.holders.DayForecast;
import wipro.weatherapp.holders.Forecast;
import wipro.weatherapp.holders.WeatherForecastData;
import wipro.weatherapp.mvp.contracts.ContractMainActivity;
import wipro.weatherapp.utils.AppSettings;
import wipro.weatherapp.utils.Utils;

public class PresenterMainActivity extends BasePresenter<ContractMainActivity.View>
        implements ContractMainActivity.Presenter {

    @Override
    public void init() {
        if (AppSettings.mSelectedCity != null) {
            getMvpView().setCityName(AppSettings.mSelectedCity.getName());
            getForecast(AppSettings.mSelectedCity.getName());
        }
        getMvpView().setSelectedUnit(AppSettings.mSelectedUnit);
    }

    @Override
    public void getForecast(@NonNull String cityName) {
        getMvpView().toggleLoadingView(true);
        RestClient.getApiService().getForecastByCityName(cityName).enqueue(new Callback<WeatherForecastData>() {
            @Override
            public void onResponse(Call<WeatherForecastData> call, Response<WeatherForecastData> response) {
                WeatherForecastData weatherData = response.body();

                List<DayForecast> daysForecast = getForecastPerDays(weatherData);
                if (daysForecast == null || daysForecast.size() < 2) {
                    getMvpView().onRequestError("Required minimum 2 days forecast");
                } else {
                    getMvpView().showForecastForThisDays(daysForecast);
                }

                AppSettings.mSelectedCity = weatherData.getCity();
                getMvpView().setCityName(AppSettings.mSelectedCity.getName());

                getMvpView().toggleLoadingView(false);
            }

            @Override
            public void onFailure(Call<WeatherForecastData> call, Throwable t) {
                getMvpView().onRequestError(t != null ? t.getMessage() : "Error");

                getMvpView().toggleLoadingView(false);
            }
        });
    }

    @Override
    public void onUnitMetricsSelected() {
        AppSettings.mSelectedUnit = AppSettings.Unit.Celsius;
        if (AppSettings.mSelectedCity != null) {
            getForecast(AppSettings.mSelectedCity.getName());
        }
    }

    @Override
    public void onUnitImperialSelected() {
        AppSettings.mSelectedUnit = AppSettings.Unit.Fahrenheit;
        if (AppSettings.mSelectedCity != null) {
            getForecast(AppSettings.mSelectedCity.getName());
        }
    }

    private List<DayForecast> getForecastPerDays(WeatherForecastData forecastData) {
        HashMap<String, List<Forecast>> mapOfForecastsByDate = new HashMap<>();
        List<Forecast> mForecasts = forecastData.getForecastList();
        List<DayForecast> daysForecast = new ArrayList<>();

        for (Forecast forecast : mForecasts) {
            String forecastDayDate = Utils.formatDateToDayMonthYear(forecast.getDateOfCalculation());
            List<Forecast> listOfForecastForThisDay = mapOfForecastsByDate.get(forecastDayDate);
            if (listOfForecastForThisDay == null) {
                listOfForecastForThisDay = new ArrayList<>();
            }
            listOfForecastForThisDay.add(forecast);
            mapOfForecastsByDate.put(forecastDayDate, listOfForecastForThisDay);
        }

        for (String date : mapOfForecastsByDate.keySet()) {
            DayForecast dayForecast = new DayForecast();
            dayForecast.setDayDate(date);
            dayForecast.setForecastList(mapOfForecastsByDate.get(date));
            daysForecast.add(dayForecast);
        }

        Comparator<DayForecast> comparator = new Comparator<DayForecast>() {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());

            @Override
            public int compare(DayForecast lhs, DayForecast rhs) {
                try {
                    return dateFormat.parse(lhs.getDayDate()).compareTo(dateFormat.parse(rhs.getDayDate()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return 0;
            }
        };

        Collections.sort(daysForecast, comparator);
        return daysForecast;
    }

}
