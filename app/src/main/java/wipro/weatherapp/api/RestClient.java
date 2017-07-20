package wipro.weatherapp.api;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import wipro.weatherapp.utils.AppSettings;

public class RestClient {

    private static ApiService mApiService;

    public static ApiService getApiService() {
        if (mApiService == null) {
            Gson gson = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                    .create();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(ApiService.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(getRetrofitClient())
                    .build();

            mApiService = retrofit.create(ApiService.class);
        }
        return mApiService;
    }

    private static OkHttpClient getRetrofitClient() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                HttpUrl originalHttpUrl = original.url();

                HttpUrl url = originalHttpUrl.newBuilder()
                        .addQueryParameter("appid", "4eaa1d3c6ab84b43b4e0b34e45afb05b")
                        .addQueryParameter("units", AppSettings.mSelectedUnit.mParamValue)
                        .build();

                Request.Builder requestBuilder = original.newBuilder().url(url);
                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        });
        return httpClient.build();
    }

}
