package pl.sda.intermediate16.weather;

import retrofit2.Retrofit;
import retrofit2.adapter.java8.Java8CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import java.net.URL;
import java.util.concurrent.CompletableFuture;

public class WeatherService {
    private String apiKey = "ea900b66f547fd7b23625544873a4200";

    public WeatherResult getWeather() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(Java8CallAdapterFactory.create())
                .build();
        OpenWeatherMapJ8 openWeatherMapJ8 = retrofit.create(OpenWeatherMapJ8.class);
        CompletableFuture<WeatherResult> cf = openWeatherMapJ8.getCurrentWeatherByCity("Łódź", apiKey, "metric", "pl");

        WeatherResult result = cf.join();
        return result;
    }

}
