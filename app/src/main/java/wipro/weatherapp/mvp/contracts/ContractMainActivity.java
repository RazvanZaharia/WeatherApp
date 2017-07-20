package wipro.weatherapp.mvp.contracts;


import android.support.annotation.NonNull;

import java.util.List;

import wipro.weatherapp.utils.AppSettings;
import wipro.weatherapp.holders.DayForecast;
import wipro.weatherapp.mvp.MvpPresenter;
import wipro.weatherapp.mvp.MvpView;

public interface ContractMainActivity {

    interface View extends MvpView {
        void onRequestError(@NonNull String message);

        void setCityName(@NonNull String cityName);

        void setSelectedUnit(@NonNull AppSettings.Unit unit);

        void showForecastForThisDays(@NonNull List<DayForecast> forecastDays);

        void toggleLoadingView(boolean show);
    }

    interface Presenter extends MvpPresenter<View> {
        void init();

        void getForecast(@NonNull String cityName);

        void onUnitMetricsSelected();

        void onUnitImperialSelected();
    }

}
