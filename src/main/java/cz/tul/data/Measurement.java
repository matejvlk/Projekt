package cz.tul.data;

import org.springframework.data.annotation.Id;

public class Measurement {

    @Id
    public String id;

    public int cityId;
    public String cityName;

    public String weatherMain;
    public String weatherDescription;
    public double temperature;
    public double pressure;

    public Measurement() {}

    public Measurement(int cityId, String cityName, String weatherMain, String weatherDescription, double temperature, double pressure) {
        this.cityId = cityId;
        this.cityName = cityName;
        this.weatherMain = weatherMain;
        this.weatherDescription = weatherDescription;
        this.temperature = temperature;
        this.pressure = pressure;
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

    public String getWeatherMain() {
        return weatherMain;
    }

    public void setWeatherMain(String weatherMain) {
        this.weatherMain = weatherMain;
    }

    public String getWeatherDescription() {
        return weatherDescription;
    }

    public void setWeatherDescription(String weatherDescription) {
        this.weatherDescription = weatherDescription;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    @Override
    public String toString() {
        return "Measurement [cityId=" + cityId + ", cityName=" + cityName + ", weatherMain=" + weatherMain + ", weatherDescription=" + weatherDescription + ", temperature=" + temperature + ", pressure=" + pressure + "]";
    }
}
