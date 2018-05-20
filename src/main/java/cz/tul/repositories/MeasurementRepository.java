package cz.tul.repositories;

import java.util.List;

import cz.tul.data.Measurement;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeasurementRepository extends MongoRepository<Measurement, String> {

    List<Measurement> findByCityName(String cityName);

    //void deleteById(int id);

    //boolean existsById(int id);

}