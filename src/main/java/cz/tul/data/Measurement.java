package cz.tul.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection = Measurement.COLLECTION_NAME)
public class Measurement {
    public static final String COLLECTION_NAME = "measurements";
    public static final String OBJECT_ID = "objectId";
    public static final String DATE = "date";
    public static final String CITY_ID = "id";
    public static final String CITY_NAME = "name";
    public static final String WEATHER = "weather";
    public static final String DESCRIPTION = "description";
    public static final String TEMP = "temp";
    public static final String PRESSURE = "pressure";

    @Id
    @JsonProperty(OBJECT_ID)
    public String id;

    @JsonProperty(DATE)
    public Date date = new Date();

    @JsonProperty(CITY_ID)
    public int cityId;

    @JsonProperty(CITY_NAME)
    public String cityName;

    @JsonProperty(WEATHER)
    public String weather;

    @JsonProperty(DESCRIPTION)
    public String description;

    @JsonProperty(TEMP)
    public double temp;

    @JsonProperty(PRESSURE)
    public double pressure;

    public Measurement() {}

    public Measurement(int cityId, String cityName, String weather, String description, double temp, double pressure) {
        this.cityId = cityId;
        this.cityName = cityName;
        this.weather = weather;
        this.description = description;
        this.temp = temp;
        this.pressure = pressure;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    @Override
    public String toString() {
        return "Measurement{" +
                "id='" + id + '\'' +
                ", date=" + date +
                ", cityId=" + cityId +
                ", cityName='" + cityName + '\'' +
                ", weather='" + weather + '\'' +
                ", description='" + description + '\'' +
                ", temp=" + temp +
                ", pressure=" + pressure +
                '}';
    }
}
