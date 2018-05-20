package cz.tul.service;

import cz.tul.data.MainWeather;
import cz.tul.data.Measurement;
import cz.tul.repositories.MeasurementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MeasurementService {

    @Autowired
    private MeasurementRepository measurementRepository;

    public List<Measurement> getByCityName(String cityName)
    {
        return measurementRepository.findByCityName(cityName);
    }

    public Measurement getLastForCityName(String cityName){
        List<Measurement> all = measurementRepository.findByCityName(cityName);
        if(all != null && all.size() > 0){
            Measurement output = all.get(0);
            if(all.size() > 1){
                for(int i = 1; i < all.size(); i++){
                    if(all.get(i).getDate().after(output.getDate())){
                        output = all.get(i);
                    }
                }
            }
            return output;
        }
        else{
            return null;
        }
    }

    public List<Measurement> getLastMeasurements(){
        List<Measurement> all = measurementRepository.findAll();

        if(all != null && all.size() > 0){
            List<String> cityNames = new ArrayList<String>();
            List<Measurement> lastMeasurements = new ArrayList<Measurement>();

            for(Measurement measurement : all){
                if(!cityNames.contains(measurement.getCityName())){
                    cityNames.add(measurement.getCityName());
                }
            }

            for(String cityName : cityNames){
                List<Measurement> allForCity = measurementRepository.findByCityName(cityName);
                Measurement output = allForCity.get(0);
                if(allForCity.size() > 1){
                    for(int i = 1; i < allForCity.size(); i++){
                        if(all.get(i).getDate().after(output.getDate())){
                            output = all.get(i);
                        }
                    }
                }
                lastMeasurements.add(output);
            }
            return lastMeasurements;
        }else{
            return null;
        }
    }

    public List<Measurement> getLastDaysMeasurements(int days){
        List<Measurement> all = measurementRepository.findAll();

        if(all != null && all.size() > 0){
            List<Measurement> output = new ArrayList<Measurement>();

            long timeSpan = 1000 * 60 * 60 * 24 * days;
            long treshold = new Date().getTime() - timeSpan;

            for(Measurement measurement : all){
                if(measurement.getDate().getTime() > treshold){
                    output.add(measurement);
                }
            }
            return output;
        }else{
            return null;
        }
    }

    public Measurement getAverageMeasurement(String cityName, int days){
        List<Measurement> all = measurementRepository.findByCityName(cityName);

        if(all != null && all.size() > 0){
            Measurement output = new Measurement();

            long timeSpan = 1000 * 60 * 60 * 24 * days;
            long treshold = new Date().getTime() - timeSpan;

            double temperature = 0;
            double pressure = 0;
            int count = 0;

            for(Measurement measurement : all){
                if(measurement.getDate().getTime() > treshold){
                    temperature += measurement.main.temp;
                    pressure += measurement.main.pressure;
                    count++;
                }
            }

            output.setMain(new MainWeather(temperature/count, pressure/count));
            output.setCityName(cityName);

            return output;
        }else{
            return null;
        }
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
