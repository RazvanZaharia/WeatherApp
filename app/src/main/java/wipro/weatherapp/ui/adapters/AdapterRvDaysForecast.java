package wipro.weatherapp.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import wipro.weatherapp.R;
import wipro.weatherapp.holders.DayForecast;
import wipro.weatherapp.holders.Forecast;
import wipro.weatherapp.ui.adapters.AdapterRvDaysForecast.DayForecastViewHolder;
import wipro.weatherapp.utils.AppSettings;
import wipro.weatherapp.utils.Utils;

public class AdapterRvDaysForecast extends RecyclerView.Adapter<DayForecastViewHolder> {

    private List<DayForecast> mDataSet;

    public AdapterRvDaysForecast(List<DayForecast> dataSet) {
        mDataSet = dataSet;
    }

    @Override
    public DayForecastViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return DayForecastViewHolder.newInstance(parent);
    }

    @Override
    public void onBindViewHolder(DayForecastViewHolder holder, int position) {
        holder.bind(mDataSet.get(position));
    }

    @Override
    public int getItemCount() {
        return mDataSet == null ? 0 : mDataSet.size();
    }

    static final class DayForecastViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_date)
        TextView mTvDayDate;
        @BindView(R.id.tv_temperature)
        TextView mTvDayTemperature;
        @BindView(R.id.tv_description)
        TextView mTvDescription;

        public static DayForecastViewHolder newInstance(ViewGroup parent) {
            return new DayForecastViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_day_forecast, parent, false));
        }

        public DayForecastViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(DayForecast dayForecast) {
            mTvDayDate.setText(Utils.formatDateMonthFullDayName(dayForecast.getDayDate()));

            if (dayForecast.getForecastList() != null && dayForecast.getForecastList().size() > 0) {

                int middleIndex = (dayForecast.getForecastList().size() - 1) / 2;
                Forecast middleForecast = dayForecast.getForecastList().get(middleIndex);

                int maxTemp = middleForecast.getTemperature().getMaximumTemperature();
                String temp = String.format(itemView.getContext().getString(R.string.value_temperature), maxTemp, AppSettings.mSelectedUnit.mSymbol);
                mTvDayTemperature.setText(temp);

                if (middleForecast.getWeatherConditionsList() != null
                        && middleForecast.getWeatherConditionsList().size() > 0) {
                    mTvDescription.setText(middleForecast.getWeatherConditionsList().get(0).getDescription());
                }
            }
        }
    }
}
