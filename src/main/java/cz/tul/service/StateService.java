package cz.tul.service;

import cz.tul.data.State;
import cz.tul.repositories.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class StateService {

    @Autowired
    private StateRepository stateRepository;

    public void save(State state) {
        stateRepository.save(state);
    }

    public boolean exists(int id) {
        return stateRepository.existsById(id);
    }

    public List<State> getAllStates() {
        return StreamSupport.stream(stateRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    public State getStateByName(String stateName){
        return stateRepository.findByStateName(stateName);
    }

    public void deleteStates() {
        stateRepository.deleteAll();
    }

    public void deleteById(int id){
        stateRepository.deleteById(id);
    }
}
