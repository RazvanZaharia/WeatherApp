package wipro.weatherapp.ui.adapters;


import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import wipro.weatherapp.R;
import wipro.weatherapp.holders.Forecast;
import wipro.weatherapp.ui.adapters.AdapterRvHourlyForecast.HourlyForecastItemViewHolder;
import wipro.weatherapp.utils.AppSettings;
import wipro.weatherapp.utils.Utils;

public class AdapterRvHourlyForecast extends RecyclerView.Adapter<HourlyForecastItemViewHolder> {

    private List<Forecast> mDataSet;

    public AdapterRvHourlyForecast(List<Forecast> dataSet) {
        mDataSet = dataSet;
    }

    @Override
    public HourlyForecastItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return HourlyForecastItemViewHolder.newInstance(parent);
    }

    @Override
    public void onBindViewHolder(HourlyForecastItemViewHolder holder, int position) {
        holder.bind(mDataSet.get(position));
    }

    @Override
    public int getItemCount() {
        return mDataSet == null ? 0 : mDataSet.size();
    }

    static final class HourlyForecastItemViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_hour_temp)
        TextView mTvHourTemp;
        @BindView(R.id.iv_hour_indicator)
        ImageView mIvHourWeatherIndicator;
        @BindView(R.id.tv_time)
        TextView mTvWeatherForTime;

        public static HourlyForecastItemViewHolder newInstance(ViewGroup parent) {
            return new HourlyForecastItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hourly_forecast, parent, false));
        }

        public HourlyForecastItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(Forecast forecast) {
            String hourOfForecast = Utils.formatDateToHour(forecast.getDateOfCalculation());
            if (!TextUtils.isEmpty(hourOfForecast)) {
                mTvWeatherForTime.setText(hourOfForecast);
            }

            mTvHourTemp.setText(String.format(itemView.getContext().getString(R.string.value_temperature),
                    forecast.getTemperature().getCurrentTemperature(), AppSettings.mSelectedUnit.mSymbol));

            Glide.with(itemView.getContext()).load("").into(mIvHourWeatherIndicator);
        }
    }
}
