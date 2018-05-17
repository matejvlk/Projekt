package cz.tul.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MainWeather {

    @JsonProperty("temp")
    public double temp;
    @JsonProperty("pressure")
    public double pressure;

    public MainWeather(){}

    public MainWeather(double temp, double pressure) {
        this.temp = temp;
        this.pressure = pressure;
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
        return "MainWeather{" +
                "temp=" + temp +
                ", pressure=" + pressure +
                '}';
    }
}
