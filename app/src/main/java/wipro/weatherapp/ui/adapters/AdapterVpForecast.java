package wipro.weatherapp.ui.adapters;


import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

import wipro.weatherapp.R;
import wipro.weatherapp.holders.DayForecast;
import wipro.weatherapp.ui.fragments.FragmentMultipleDaysForecast;
import wipro.weatherapp.ui.fragments.FragmentOneDayForecast;

public class AdapterVpForecast extends FragmentStatePagerAdapter {

    private List<DayForecast> mDaysForecastDataSet;
    private Context mCtx;

    public AdapterVpForecast(Context context, FragmentManager fm, List<DayForecast> dataSet) {
        super(fm);

        this.mCtx = context;
        mDaysForecastDataSet = dataSet;

        if (mDaysForecastDataSet == null || mDaysForecastDataSet.size() < 2) {
            throw new IllegalArgumentException("Required minimum 2 days forecast");
        }
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0: //forecast for today
                fragment = FragmentOneDayForecast.newInstance(mDaysForecastDataSet.get(0));
                break;
            case 1: // forecast for tomorrow
                fragment = FragmentOneDayForecast.newInstance(mDaysForecastDataSet.get(1));
                break;
            case 2: // forecast for all days
                fragment = FragmentMultipleDaysForecast.newInstance(new ArrayList<>(mDaysForecastDataSet));
                break;
        }
        return fragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String pageTitle = "";
        switch (position) {
            case 0:
                pageTitle = mCtx.getString(R.string.today_forecast_title);
                break;
            case 1:
                pageTitle = mCtx.getString(R.string.tomorrow_forecast_title);
                break;
            case 2:
                pageTitle = String.format(mCtx.getString(R.string.multiple_days_forecast_title), mDaysForecastDataSet.size());
        }
        return pageTitle;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
