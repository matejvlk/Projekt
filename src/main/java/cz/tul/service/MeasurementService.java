package cz.tul.service;

import cz.tul.data.Measurement;
import cz.tul.repositories.MeasurementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
            for(int i = 0; i < all.size(); i++){
                if(all.get(i).getDate().after(output.getDate())){
                    output = all.get(i);
                }
            }
            return output;
        }
        else{
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
