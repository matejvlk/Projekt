package cz.tul.service;

import cz.tul.data.State;
import cz.tul.repositories.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@ComponentScan("cz.tul.repositories")
public class StateService {

    @Autowired
    private StateRepository stateRepository;

    public void create(State state) {
        stateRepository.save(state);
    }

    public boolean exists(String stateName) {
        return stateRepository.exists(stateName);
    }

    public List<State> getAllStates() {
        return StreamSupport.stream(stateRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    public void deleteStates() {
        stateRepository.deleteAll();
    }
}
