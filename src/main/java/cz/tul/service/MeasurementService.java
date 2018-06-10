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

        //netoestovaný kód (nový notebook) ale podle tutoriálu by to mělo být nějak takto
        MatchOperation matchOperation = Aggregation.match(new Criteria(Measurement.DATE).gt(date).and(Measurement.CITY_NAME).is(cityName));

        GroupOperation groupOperation = Aggregation.group()
                .avg(Measurement.TEMP).as(MeasurementAvg.TEMP_AVG)
                .avg(Measurement.PRESSURE).as(MeasurementAvg.PRESSURE_AVG);

        ProjectionOperation projectionOperation = Aggregation.project(MeasurementAvg.TEMP_AVG, MeasurementAvg.PRESSURE_AVG);
        projectionOperation = projectionOperation.andExclude(Measurement.CITY_ID);

        Aggregation aggregation = Aggregation.newAggregation(matchOperation, groupOperation, projectionOperation);

        AggregationResults<MeasurementAvg> output = mongoTemplate.aggregate(aggregation, Measurement.COLLECTION_NAME, MeasurementAvg.class);
        return output.getUniqueMappedResult();


        //původní funkční a otestovaný, ale ne ideální způsob:
/*
        MeasurementAvg output = new MeasurementAvg();
        output.setCityName(cityName);

        double totalTemp = 0;
        double totalPress = 0;
        int count = 0;

        //není to ideální způsob, ale agregační funkci "avg" se mi nepodařilo rozchodit
        List<Measurement> measurements = measurementRepository.findByCityNameAndDateGreaterThan(cityName,date);

        for(Measurement m : measurements){
            totalTemp += m.temp;
            totalPress += m.pressure;
            count++;
        }

        output.setTempAvg(totalTemp/count);
        output.setPressureAvg(totalPress/count);

        return output;
*/
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
