package cz.tul.controller;

import cz.tul.data.State;
import cz.tul.service.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

import java.util.List;

@Controller
public class HomeController {

    private StateService stateService;

    @Autowired
    public void setStateService(StateService stateService) {
        this.stateService = stateService;
    }

    @RequestMapping("/")
    public String showHome(Model model){
        List<State> states = stateService.getAllStates();

        model.addAttribute("states", states);

        return "home";
    }
}
