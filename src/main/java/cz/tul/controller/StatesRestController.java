package cz.tul.controller;

import java.util.List;

import cz.tul.data.State;
import cz.tul.service.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class StatesRestController {

    private StateService stateService;

    @Autowired
    public void setStateService(StateService stateService) {
        this.stateService = stateService;
    }

    @GetMapping("/states")
    public List<State> states() {
        return stateService.getAllStates();
    }

    @GetMapping("/statedetail/{state}")
    public State stateDetail(@PathVariable(name="state", required=true) String stateName) {
        return stateService.getStateByName(stateName);
    }

    @PostMapping("/states")
    public void saveState(@RequestBody State state) {
        stateService.create(state);
    }

    @DeleteMapping("states/{id}")
    public void deleteState(@PathVariable("id") int id){
        stateService.deleteById(id);
    }

}
