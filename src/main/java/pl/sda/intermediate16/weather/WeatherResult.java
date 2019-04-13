
package pl.sda.intermediate16.weather;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WeatherResult {

    @SerializedName("coord")
    //ta adnotacja jest tutaj niepotrzebna bo jesli nazwa coord jest tutaj taka sama jak w json
    @Expose
    //Expose jest deafult  jeśli nie ustawimy inaczej
    public Coord coord;
    @SerializedName("weather")
    //jeśli chcemy usunąć wszystkie to regexem je usuwamy w jntelij
    @Expose
    public List<Weather> weather = null;
    @SerializedName("base")
    @Expose
    public String base;
    @SerializedName("main")
    @Expose
    public Main main;
    @SerializedName("visibility")
    @Expose
    public Integer visibility;
    @SerializedName("wind")
    @Expose
    public Wind wind;
    @SerializedName("clouds")
    @Expose
    public Clouds clouds;
    @SerializedName("dt")
    @Expose
    public Integer dt;
    @SerializedName("sys")
    @Expose
    public Sys sys;
    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("cod")
    @Expose
    public Integer cod;

}
