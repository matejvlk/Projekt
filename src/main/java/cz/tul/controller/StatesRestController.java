package cz.tul.controller;

import java.util.List;

import cz.tul.data.State;
import cz.tul.service.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class StatesRestController {

    private StateService stateService;

    @Autowired
    public void setStateService(StateService stateService) {
        this.stateService = stateService;
    }

    @RequestMapping(value = "/states", method = RequestMethod.GET)
    public List<State> states() {
        return stateService.getAllStates();
    }

    @RequestMapping(value = "/statedetail/{state}", method = RequestMethod.GET)
    public State stateDetail(@PathVariable(name="state", required=true) String stateName) {
        return stateService.getStateByName(stateName);
    }
}
