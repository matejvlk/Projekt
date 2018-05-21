package cz.tul.data;

import com.fasterxml.jackson.annotation.JsonProperty;

//Projekce
public class MeasurementAvg {
    public static final String CITY_NAME = "cityName";
    public static final String TEMP_AVG = "tempAvg";
    public static final String PRESSURE_AVG = "pressureAvg";

    @JsonProperty(CITY_NAME)
    public String cityName;
    @JsonProperty(TEMP_AVG)
    public double tempAvg;
    @JsonProperty(PRESSURE_AVG)
    public double pressureAvg;

    public MeasurementAvg(){}

    public MeasurementAvg(String cityName, double tempAvg, double pressureAvg) {
        this.cityName = cityName;
        this.tempAvg = tempAvg;
        this.pressureAvg = pressureAvg;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public double getTempAvg() {
        return tempAvg;
    }

    public void setTempAvg(double tempAvg) {
        this.tempAvg = tempAvg;
    }

    public double getPressureAvg() {
        return pressureAvg;
    }

    public void setPressureAvg(double pressureAvg) {
        this.pressureAvg = pressureAvg;
    }

    @Override
    public String toString() {
        return "MeasurementAvg{" +
                "cityName='" + cityName + '\'' +
                ", tempAvg=" + tempAvg +
                ", pressureAvg=" + pressureAvg +
                '}';
    }
}
