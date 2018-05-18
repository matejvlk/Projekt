package cz.tul.controller;

import cz.tul.data.City;
import cz.tul.data.State;
import cz.tul.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CitiesRestController {

    private CityService cityService;

    @Autowired
    public void setCityService(CityService cityService) {
        this.cityService = cityService;
    }

    @RequestMapping(value = "/cities", method = RequestMethod.GET)
    public List<City> cities() {
        return cityService.getCities();
    }

    @RequestMapping(value = "/citydetail/{city}", method = RequestMethod.GET)
    public City cityDetail(@PathVariable(name="city", required=true) String cityName) {
        return cityService.getCityByCityName(cityName);
    }

    @PostMapping("/cities")
    public void saveCity(@RequestBody City city) {
        cityService.create(city);
    }

    @DeleteMapping("/cities/{id}")
    public void deleteCity(@PathVariable("id") int id) {
        cityService.delete(id);
    }
}
