
package cz.tul;

import cz.tul.data.City;
import cz.tul.data.Measurement;
import cz.tul.data.State;
import cz.tul.data.Weather;
import cz.tul.repositories.MeasurementRepository;
import cz.tul.service.CityService;
import cz.tul.service.StateService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class App {

    public static void main(String[] args) {
        new App().run();
    }


    private void run(){

        SpringApplication app = new SpringApplication(App.class);
        ApplicationContext ctx = app.run();

        /*
        //states
        StateService stateService = ctx.getBean(StateService.class);

        stateService.deleteStates();

        State czech = new State("Česká republika", true, "user");
        stateService.save(czech);

        State usa = new State("USA", true, "user");
        stateService.save(usa);

        List<State> states = stateService.getAllStates();
        System.out.println(states);

        //cities
        CityService cityService = ctx.getBean(CityService.class);

        cityService.deleteCities();

        City liberec = new City(czech, 3071961, "Liberec");
        cityService.save(liberec);

        City praha = new City(czech, 3067695, "Praha");
        cityService.save(praha);

        City newYork = new City(usa, 5128638, "New York");
        cityService.save(newYork);

        List<City> cities = cityService.getCities();
        System.out.println(cities);

        */
    }

}