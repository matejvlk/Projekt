package cz.tul.service;

import cz.tul.data.State;
import cz.tul.repositories.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class StateService {

    @Autowired
    private StateRepository stateRepository;

    public void create(State state) {
        stateRepository.save(state);
    }

    public boolean exists(int id) {
        List<State> states = getAllStates();
        for (State s: states) {
            if(s.getId() == id){
                return true;
            }
        }
        return false;
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
