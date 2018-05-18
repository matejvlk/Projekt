package cz.tul.repositories;

import cz.tul.data.State;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface StateRepository extends CrudRepository<State, String> {

    State findByStateName(String stateName);

    @Transactional
    void deleteById(int id);
}