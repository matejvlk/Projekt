package cz.tul.service;

import cz.tul.data.City;
import cz.tul.data.Measurement;
import cz.tul.data.MeasurementAvg;
import cz.tul.repositories.MeasurementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;
import org.springframework.data.mongodb.core.aggregation.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;

@Service
public class MeasurementService {

    static final long MILLIS_IN_DAY = 1000 * 60 * 60 * 24;

    private MongoTemplate mongoTemplate;
    private MeasurementRepository measurementRepository;
    private CityService cityService;

    @Autowired
    public MeasurementService(MongoTemplate mongoTemplate, MeasurementRepository measurementRepository, CityService cityService){
        this.mongoTemplate = mongoTemplate;
        this.measurementRepository = measurementRepository;
        this.cityService = cityService;
    }

    public List<Measurement> getByCityName(String cityName)
    {
        return measurementRepository.findByCityName(cityName);
    }

    public List<Measurement> getActualForState(String stateName){
        List<City> cities = cityService.getCitiesByStateName(stateName);

        List<Measurement> measurements = new ArrayList<Measurement>();

        for(City city : cities){
            measurements.add(getLastForCityName(city.getCityName()));
        }

        return measurements;
    }

    public Measurement getLastForCityName(String cityName){
        return measurementRepository.findFirstByCityNameOrderByDateDesc(cityName);
    }

    public MeasurementAvg getAverageMeasurement(String cityName, int days){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, -days);
        Date date = calendar.getTime();

        Aggregation aggregation = Aggregation.newAggregation(
                //match(Criteria.where(Measurement.DATE).gt(date).and(Measurement.CITY_NAME).is(cityName)),
                match(Criteria.where(Measurement.DATE).gt(date)),
                //match(Criteria.where(Measurement.CITY_NAME).is(cityName)),
                group()
                        .avg(Measurement.TEMP).as(MeasurementAvg.TEMP_AVG)
                        .avg(Measurement.PRESSURE).as(MeasurementAvg.PRESSURE_AVG)
        );

        AggregationResults<MeasurementAvg> result = mongoTemplate.aggregate(aggregation, Measurement.COLLECTION_NAME, MeasurementAvg.class);
        MeasurementAvg output = result.getUniqueMappedResult();
        return output;
    }

    public void save(Measurement measurement)
    {
        measurementRepository.save(measurement);
    }

    public boolean exists(String id) {
        return measurementRepository.existsById(id);
    }

    public List<Measurement> findAll(){
        return measurementRepository.findAll();
    }

    public void deleteById(String id){
        measurementRepository.deleteById(id);
    }

    public void deleteAll(){
        measurementRepository.deleteAll();
    }
}
