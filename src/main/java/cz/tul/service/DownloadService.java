package cz.tul.service;

import com.jayway.jsonpath.JsonPath;
import cz.tul.Config.Conditions;
import cz.tul.data.City;
import cz.tul.data.Measurement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Conditional;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Conditional(Conditions.ReadOnlyModeDisabled.class)
@Service
public class DownloadService {
    //private final long updateInterval = 1000*60*10; // 10 min
    private final long updateInterval = 1000*20;

    private CityService cityService;
    private MeasurementService measurementService;

    private ThreadPoolTaskScheduler threadPoolTaskScheduler;

    @Autowired
    public DownloadService(CityService cityService, MeasurementService measurementService, ThreadPoolTaskScheduler threadPoolTaskScheduler){
        this.cityService = cityService;
        this.measurementService = measurementService;
        this.threadPoolTaskScheduler = threadPoolTaskScheduler;
        startUpdatingData();
    }

    private void startUpdatingData(){
        threadPoolTaskScheduler.scheduleAtFixedRate(this::downloadMeasrurements, updateInterval);
    }

    private void downloadMeasrurements(){
        RestTemplate restTemplate = new RestTemplate();
        List<City> cities = cityService.getCities();

        for(City city : cities){
            String url = "http://api.openweathermap.org/data/2.5/weather?id=";
            url += String.valueOf(city.getCityId());
            url += "&units=metric&APPID=383b888b2b81f8060f1f57453315b655";

            String restResponse = restTemplate.getForObject(url, String.class);
            Measurement measurement = parseMeasurement(restResponse);

            measurementService.save(measurement);
        }
    }

    private Measurement parseMeasurement(String input){
        int cityId = JsonPath.read(input, "$.id");
        String cityName = JsonPath.read(input, "$.name");
        String weather = JsonPath.read(input, "$.weather[0].main");
        String description = JsonPath.read(input, "$.weather[0].description");
        double temp = toDouble(JsonPath.read(input, "$.main.temp"));
        double pressure = toDouble(JsonPath.read(input, "$.main.pressure"));

        return new Measurement(cityId, cityName, weather, description, temp, pressure);
    }

    private double toDouble(Object input){
        double output;
        if (input instanceof Double) {
            output = (Double) input;
        } else {
            output = ((Integer) input).doubleValue();
        }
        return output;
    }
}
