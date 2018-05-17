package cz.tul.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Measurement {

    @Id
    @JsonIgnore
    public String id;

    @JsonProperty("id")
    public int cityId;
    @JsonProperty("name")
    public String cityName;

    @JsonProperty("weather")
    public List<Weather> weather;

    @JsonProperty("main")
    public MainWeather main;

    public Measurement() {}

    public Measurement(int cityId, String cityName, List<Weather> weather, MainWeather main) {
        this.cityId = cityId;
        this.cityName = cityName;
        this.weather = weather;
        this.main = main;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    public MainWeather getMain() {
        return main;
    }

    public void setMain(MainWeather main) {
        this.main = main;
    }

    @Override
    public String toString() {
        return "Measurement{" +
                "id='" + id + '\'' +
                ", cityId=" + cityId +
                ", cityName='" + cityName + '\'' +
                ", weather=" + weather +
                ", main=" + main +
                '}';
    }
}
