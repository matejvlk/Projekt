package cz.tul.service;

import cz.tul.data.City;
import cz.tul.repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    public List<City> getCities() {
        List<City> output = new ArrayList<City>();
        List<City> all = StreamSupport.stream(cityRepository.findAll().spliterator(), false).collect(Collectors.toList());

        for (City city : all){
            if(city.getState().isEnabled()){
                output.add(city);
            }
        }

        return output;
    }

    public void create(City city)
    {
        cityRepository.save(city);
    }

    public boolean hasCity(String stateName) {
        if (stateName == null) {
            return false;
        }

        return cityRepository.findByStateName(stateName).size() != 0;
    }

    public List<City> getCitiesByName(String stateName) {
        if (stateName == null) {
            return null;
        }

        List<City> cities = cityRepository.findByStateName(stateName);

        if (cities.size() == 0) {
            return null;
        }

        return cities;
    }

    public City getCity(Integer id)
    {
        City city = cityRepository.findOne(id);
        if(city == null || !city.getState().isEnabled()){
            return null;
        }
        else{
            return city;
        }
    }

    public void saveOrUpdate(City city)
    {
        cityRepository.save(city);
    }

    public void delete(int id)
    {
        cityRepository.delete(id);
    }

    public void deleteCities()
    {
        cityRepository.deleteAll();
    }
}
