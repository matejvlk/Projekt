package cz.tul.service;

import cz.tul.data.City;
import cz.tul.repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    public List<City> getCities() {
        return StreamSupport.stream(cityRepository.findAll().spliterator(), false).collect(Collectors.toList());
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
        return cityRepository.findOne(id);
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
