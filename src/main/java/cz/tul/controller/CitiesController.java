package cz.tul.controller;

import cz.tul.data.City;
import cz.tul.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CitiesController {

    private CityService cityService;

    @Autowired
    public void setCityService(CityService cityService) {
        this.cityService = cityService;
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String showTest(Model model, @RequestParam("id") String id) {
        System.out.println("Id is: " + id);
        return "home";
    }

    @RequestMapping("/cities")
    public String showCities(Model model) {

        List<City> cities = cityService.getCities();

        model.addAttribute("cities", cities);

        return "cities";
    }

    @RequestMapping("/createcity")
    public String createCity() {

        return "createcity";
    }
}
