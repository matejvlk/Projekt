package cz.tul.controller;

import cz.tul.data.Measurement;
import cz.tul.repositories.MeasurementRepository;
import cz.tul.service.MeasurementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MeasurementsRestController {

    @Autowired
    private MeasurementService measurementService;

    @RequestMapping(value = "/measurements", method = RequestMethod.GET)
    public List<Measurement> measurements() {
        return measurementService.findAll();
    }

    @PostMapping("/measurements")
    public void saveMeasurement(@RequestBody Measurement measurement) {
        measurementService.save(measurement);
    }

    @PutMapping("measurements/{id}")
    public ResponseEntity<Object> updateMeasurement(@RequestBody Measurement measurement, @PathVariable String id){
        if(!measurementService.exists(id)){
            return ResponseEntity.notFound().build();
        }

        measurement.setId(id);
        measurementService.save(measurement);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("measurements/{id}")
    public void deleteMeasurement(@PathVariable("id") String id){
        measurementService.deleteById(id);
    }

    @DeleteMapping("deleteallmeasurements")
    public void deleteAllMeasurements(){
        measurementService.deleteAll();
    }
}
