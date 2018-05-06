package cz.tul.controller;

import cz.tul.data.City;
import cz.tul.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class MyController {
/*
    @Autowired
    CityService cityService;

    @RequestMapping("/showCities")
    public ModelAndView findCities() {

        List<City> cities = (List<City>) cityService.getCities();

        Map<String, Object> params = new HashMap<>();
        params.put("cities", cities);

        return new ModelAndView("showCities", params);
    }*/
}
