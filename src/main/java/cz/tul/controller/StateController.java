package cz.tul.controller;

import cz.tul.data.Measurement;
import cz.tul.service.CityService;
import cz.tul.service.MeasurementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class StateController {
    private static final long MILLIS_IN_10_MINUTES = 1000*60*10;

    @Autowired
    private CityService cityService;

    @Autowired
    private MeasurementService measurementService;

    @RequestMapping(value="/state/{state}", method = RequestMethod.GET)
    public String showState(@PathVariable(name="state", required=true) String state, Model model) {
        List<Measurement> measurements = measurementService.getActualForState(state);

        model.addAttribute("state", state);
        model.addAttribute("measurements", measurements);

        return "state";
    }
}
