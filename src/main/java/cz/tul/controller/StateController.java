package cz.tul.controller;

import cz.tul.data.City;
import cz.tul.data.Measurement;
import cz.tul.data.State;
import cz.tul.repositories.MeasurementRepository;
import cz.tul.service.CityService;
import cz.tul.service.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;


@Controller
public class StateController {

    private CityService cityService;
    private MeasurementRepository measurementRepository;

    @Autowired
    public void setCityService(CityService cityService) {
        this.cityService = cityService;
    }

    @Autowired
    public void setMeasurementRepository(MeasurementRepository measurementRepository){
        this.measurementRepository = measurementRepository;
    }
/*
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }
*/

    @RequestMapping(value="/state/{state}", method = RequestMethod.GET)
    public String showState(@PathVariable(name="state", required=true) String state, Model model) {

        List<City> cities = cityService.getCitiesByName(state);

        List<Measurement> measurements = new ArrayList<Measurement>();

        RestTemplate restTemplate = new RestTemplate();

        for(City city : cities){
            String url = "http://api.openweathermap.org/data/2.5/weather?id=";
            url += String.valueOf(city.getCityId());
            url += "&units=metric&APPID=383b888b2b81f8060f1f57453315b655";
            Measurement measurement = restTemplate.getForObject(url, Measurement.class);
            measurementRepository.save(measurement);
            measurements.add(measurement);
        }

        model.addAttribute("state", state);
        model.addAttribute("measurements", measurements);

        return "state";
    }
}