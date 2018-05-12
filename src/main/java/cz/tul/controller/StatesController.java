package cz.tul.controller;

import cz.tul.data.State;
import cz.tul.service.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
public class StatesController {

    private StateService stateService;

    @Autowired
    public void setStateService(StateService stateService) {
        this.stateService = stateService;
    }

    @RequestMapping("/states")
    public String showStates(Model model) {

        List<State> states = stateService.getAllStates();

        model.addAttribute("states", states);

        return "states";
    }

}
