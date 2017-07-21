package wipro.weatherapp.ui.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import wipro.weatherapp.R;
import wipro.weatherapp.holders.DayForecast;
import wipro.weatherapp.holders.Forecast;
import wipro.weatherapp.ui.adapters.AdapterRvHourlyForecast;
import wipro.weatherapp.utils.AppSettings;
import wipro.weatherapp.utils.Utils;

import static wipro.weatherapp.utils.AppSettings.mSelectedUnit;

public class FragmentOneDayForecast extends Fragment {
    private static final String ARG_DAY_FORECAST = "argDayForecast";

    @BindView(R.id.tv_date_time)
    TextView mTvDateTime;
    @BindView(R.id.tv_min_max_temp)
    TextView mTvMinMaxTemperature;
    @BindView(R.id.tv_current_temp)
    TextView mTvCurrentTemperature;
    @BindView(R.id.iv_indicator)
    ImageView mIvWeatherIndicator;
    @BindView(R.id.tv_description)
    TextView mTvWeatherDescription;
    @BindView(R.id.rv_hourly_forecast)
    RecyclerView mRvHourlyForecast;

    private DayForecast mDayForecast;

    public static FragmentOneDayForecast newInstance(@NonNull DayForecast dayForecast) {
        FragmentOneDayForecast fragment = new FragmentOneDayForecast();
        Bundle args = new Bundle();
        args.putSerializable(ARG_DAY_FORECAST, dayForecast);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_one_day_forecast, container, false);
        ButterKnife.bind(this, fragmentView);
        return fragmentView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
        setData();
    }

    private void init() {
        mDayForecast = (DayForecast) getArguments().getSerializable(ARG_DAY_FORECAST);
        mRvHourlyForecast.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
    }

    private void setData() {
        mRvHourlyForecast.setAdapter(new AdapterRvHourlyForecast(mDayForecast.getForecastList()));
        mTvDateTime.setText(Utils.formatDateMonthDay(mDayForecast.getDayDate()));

        if (mDayForecast.getForecastList() != null && mDayForecast.getForecastList().size() > 0) {
            int middleIndex = (mDayForecast.getForecastList().size() - 1) / 2;
            Forecast forecastForNow = mDayForecast.getForecastList().get(0);
            Forecast middleForecast = mDayForecast.getForecastList().get(middleIndex);
            Forecast lastForecast = mDayForecast.getForecastList().get(mDayForecast.getForecastList().size() - 1);

            int middleForecastMaxTemp = middleForecast.getTemperature().getMaximumTemperature() > lastForecast.getTemperature().getMaximumTemperature() ?
                    middleForecast.getTemperature().getMaximumTemperature() : lastForecast.getTemperature().getMaximumTemperature();

            int minTemp = middleForecast.getTemperature().getMinimumTemperature() < lastForecast.getTemperature().getMinimumTemperature() ?
                    middleForecast.getTemperature().getMinimumTemperature() : lastForecast.getTemperature().getMinimumTemperature();

            String maxTempS = String.format(getString(R.string.value_temperature), middleForecastMaxTemp, mSelectedUnit.mSymbol);
            String minTempS = String.format(getString(R.string.value_temperature), minTemp, mSelectedUnit.mSymbol);
            mTvMinMaxTemperature.setText(String.format(getString(R.string.values_max_min), maxTempS, minTempS));

            String currentTemp = String.format(getString(R.string.value_temperature), forecastForNow.getTemperature().getCurrentTemperature(), AppSettings.mSelectedUnit.mSymbol);
            mTvCurrentTemperature.setText(currentTemp);
            if (forecastForNow.getWeatherConditionsList() != null
                    && forecastForNow.getWeatherConditionsList().size() > 0) {
                mTvWeatherDescription.setText(forecastForNow.getWeatherConditionsList().get(0).getDescription());
                Glide.with(getContext()).load(Utils.getDrawableIdentifierByServerCode(getContext(), forecastForNow.getWeatherConditionsList().get(0).getIconId())).into(mIvWeatherIndicator);
            }
        }
    }

}
