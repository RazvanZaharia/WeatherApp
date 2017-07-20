package wipro.weatherapp.ui.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import wipro.weatherapp.R;
import wipro.weatherapp.holders.DayForecast;
import wipro.weatherapp.mvp.PresenterMainActivity;
import wipro.weatherapp.mvp.contracts.ContractMainActivity;
import wipro.weatherapp.ui.adapters.AdapterVpForecast;
import wipro.weatherapp.utils.AppSettings;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        ContractMainActivity.View {

    @BindView(R.id.et_search_city)
    EditText mEtSearchCity;
    @BindView(R.id.nav_view)
    NavigationView mNavigationView;
    @BindView(R.id.tabs)
    TabLayout mTabs;
    @BindView(R.id.vp_forecast)
    ViewPager mVpForecast;

    private ContractMainActivity.Presenter mPresenter;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        mNavigationView.setNavigationItemSelectedListener(this);

        init();
        setActions();
    }

    private void init() {
        mTabs.setupWithViewPager(mVpForecast);
        mTabs.setVisibility(View.GONE);

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setTitle("Loading");
        mProgressDialog.setCancelable(false); // disable dismiss by tapping outside of the dialog

        mPresenter = new PresenterMainActivity();
        mPresenter.attachView(this);
        mPresenter.init();
    }

    private void setActions() {
        mEtSearchCity.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    mPresenter.getForecast(mEtSearchCity.getText().toString());

                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(mEtSearchCity.getWindowToken(), 0);
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id) {
            case R.id.unit_metrics:
                mPresenter.onUnitMetricsSelected();
                break;
            case R.id.unit_imperial:
                mPresenter.onUnitImperialSelected();
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onRequestError(@NonNull String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setCityName(@NonNull String cityName) {
        mEtSearchCity.setText(cityName);
        mEtSearchCity.setSelection(cityName.length());
    }

    @Override
    public void setSelectedUnit(@NonNull AppSettings.Unit unit) {
        switch (unit) {
            case Celsius:
                mNavigationView.setCheckedItem(R.id.unit_metrics);
                break;
            case Fahrenheit:
                mNavigationView.setCheckedItem(R.id.unit_imperial);
                break;
        }
    }

    @Override
    public void showForecastForThisDays(@NonNull List<DayForecast> forecastDays) {
        AdapterVpForecast adapterVpForecast = new AdapterVpForecast(this, getSupportFragmentManager(), forecastDays);
        mVpForecast.setAdapter(adapterVpForecast);
        mTabs.setVisibility(View.VISIBLE);
    }

    @Override
    public void toggleLoadingView(boolean show) {
        if (show) {
            mProgressDialog.show();
        } else {
            mProgressDialog.dismiss();
        }
    }
}