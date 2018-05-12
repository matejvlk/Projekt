
package cz.tul;

import cz.tul.data.City;
import cz.tul.data.State;
import cz.tul.service.CityService;
import cz.tul.service.StateService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.List;

@SpringBootApplication
//@EnableTransactionManagement
//@ComponentScan
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);

        //new App().run();
    }

    /*
    private void run(){
        SpringApplication app = new SpringApplication(App.class);
        ApplicationContext ctx = app.run();

        //states
        StateService stateService = ctx.getBean(StateService.class);

        State state = new State("Czech", true, "user");
        stateService.create(state);

        State state2 = new State("USA", true, "user");
        stateService.create(state2);

        List<State> states = stateService.getAllStates();
        System.out.println(states);

        //cities
        CityService cityService = ctx.getBean(CityService.class);

        City city = new City(state, "Liberec");
        cityService.saveOrUpdate(city);

        List<City> cities = cityService.getCities();
        System.out.println(cities);
    }
    */
}