package cz.tul.controller;

import cz.tul.data.Measurement;
import cz.tul.repositories.MeasurementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MeasurementsRestController {

    @Autowired
    private MeasurementRepository measurementRepository;

    @RequestMapping(value = "/measurements", method = RequestMethod.GET)
    public List<Measurement> measurements() {
        return measurementRepository.findAll();
    }

}
