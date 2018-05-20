package cz.tul.controller;

import cz.tul.data.City;
import cz.tul.data.Measurement;
import cz.tul.repositories.MeasurementRepository;
import cz.tul.service.CityService;
import cz.tul.service.MeasurementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Controller
public class StateController {

    @Autowired
    private CityService cityService;

    @Autowired
    private MeasurementService measurementService;

    /*
    @Autowired
    public void setCityService(CityService cityService) {
        this.cityService = cityService;
    }

    @Autowired
    public void setMeasurementRepository(MeasurementRepository measurementRepository){
        this.measurementRepository = measurementRepository;
    }
    */

    @RequestMapping(value="/state/{state}", method = RequestMethod.GET)
    public String showState(@PathVariable(name="state", required=true) String state, Model model) {

        List<City> cities = cityService.getCitiesByStateName(state);

        List<Measurement> measurements = new ArrayList<Measurement>();

        RestTemplate restTemplate = new RestTemplate();

        for(City city : cities){
            Measurement lastSaved = measurementService.getLastForCityName(city.getCityName());
            Date now = new Date();

            //poslední záznam je starší než 10 minut - stáhneme nový
            if(lastSaved == null || now.getTime() - lastSaved.getDate().getTime() > 1000*60*10){
                String url = "http://api.openweathermap.org/data/2.5/weather?id=";
                url += String.valueOf(city.getCityId());
                url += "&units=metric&APPID=383b888b2b81f8060f1f57453315b655";
                Measurement measurement = restTemplate.getForObject(url, Measurement.class);
                measurement.setDate(new Date());
                measurementService.save(measurement);
                measurements.add(measurement);
            }
            else{ //použijeme uložený
                measurements.add(lastSaved);
            }
        }

        model.addAttribute("state", state);
        model.addAttribute("measurements", measurements);

        return "state";
    }
}
