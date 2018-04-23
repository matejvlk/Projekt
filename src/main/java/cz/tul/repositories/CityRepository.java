package cz.tul.repositories;

import cz.tul.data.City;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends CrudRepository<City, Integer> {

    //Query using JPQL
//    @Query("from City where state.stateName=? and state.enabled=true")
//    public List<City> findByStateName(String stateName);

    //Query using JPQL and named parameter
    @Query("select c from City as c where state.stateName=:stateName and state.enabled=true")
    public List<City> findByStateName(@Param("stateName") String stateName);
}
