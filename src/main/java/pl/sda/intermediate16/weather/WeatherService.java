package pl.sda.intermediate16.weather;

import pl.sda.intermediate16.users.UserContextHolder;
import pl.sda.intermediate16.users.UserDAO;
import retrofit2.Retrofit;
import retrofit2.adapter.java8.Java8CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import java.net.URL;
import java.util.concurrent.CompletableFuture;

public class WeatherService {
    private String apiKey = "ea900b66f547fd7b23625544873a4200";
    private UserDAO userDAO;

    public WeatherService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public WeatherResult getWeather() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(Java8CallAdapterFactory.create())
                .build();
        OpenWeatherMapJ8 openWeatherMapJ8 = retrofit.create(OpenWeatherMapJ8.class);
        String email = UserContextHolder.getUserLoggedIn();
        String city = userDAO.getUserList().stream()
                .filter(u -> u.getLogin().equals(email))
                .findAny()
                .map(user -> user.getUserAddress().getCity())
                .orElse("unlocated");
        CompletableFuture<WeatherResult> cf =
                openWeatherMapJ8.getCurrentWeatherByCity(city, apiKey, "metric", "pl");

        WeatherResult result = cf.join();
        return result;
    }

}
