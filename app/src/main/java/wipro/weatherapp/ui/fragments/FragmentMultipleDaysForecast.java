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

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import wipro.weatherapp.R;
import wipro.weatherapp.holders.DayForecast;
import wipro.weatherapp.ui.adapters.AdapterRvDaysForecast;

public class FragmentMultipleDaysForecast extends Fragment {
    private static final String ARG_MULTIPLE_DAY_FORECAST = "argMultipleDayForecast";

    @BindView(R.id.rv_daily_forecast)
    RecyclerView mRvDailyForecast;

    List<DayForecast> mDaysForecasts;

    public static FragmentMultipleDaysForecast newInstance(@NonNull ArrayList<DayForecast> multipleDaysForecast) {
        FragmentMultipleDaysForecast fragment = new FragmentMultipleDaysForecast();
        Bundle args = new Bundle();
        args.putSerializable(ARG_MULTIPLE_DAY_FORECAST, multipleDaysForecast);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_multiple_days_forecast, container, false);
        ButterKnife.bind(this, fragmentView);
        return fragmentView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
        setActions();
    }

    private void init() {
        mDaysForecasts = (List<DayForecast>) getArguments().getSerializable(ARG_MULTIPLE_DAY_FORECAST);

        mRvDailyForecast.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void setActions() {
        mRvDailyForecast.setAdapter(new AdapterRvDaysForecast(mDaysForecasts));
    }

}
