
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
//@EnableTransactionManagement
//@ComponentScan
public class App {

    public static void main(String[] args) {
        //SpringApplication.run(App.class, args);

        new App().run();
    }


    private void run(){
        SpringApplication app = new SpringApplication(App.class);
        ApplicationContext ctx = app.run();

        //states
        StateService stateService = ctx.getBean(StateService.class);

        State czech = new State("Česká republika", true, "user");
        stateService.create(czech);

        State usa = new State("USA", true, "user");
        stateService.create(usa);

        List<State> states = stateService.getAllStates();
        System.out.println(states);

        //cities
        CityService cityService = ctx.getBean(CityService.class);

        City liberec = new City(czech, 3071961, "Liberec");
        cityService.saveOrUpdate(liberec);

        City praha = new City(czech, 3067695, "Praha");
        cityService.saveOrUpdate(praha);

        City newYork = new City(usa, 5128638, "New York");
        cityService.saveOrUpdate(newYork);

        List<City> cities = cityService.getCities();
        System.out.println(cities);

        /*
        //measurements
        MeasurementRepository measurementRepository = ctx.getBean(MeasurementRepository.class);

        //measurementRepository.deleteAll();

        Weather w1 = new Weather("Déšť", "Docela velikej");
        List<Weather> list = new ArrayList<Weather>();
        list.add(w1);
        measurementRepository.save(new Measurement(3071961, "Liberec", list, ));

        for(Measurement m : measurementRepository.findAll()){
            System.out.println(m);
        }


        RestTemplate restTemplate = new RestTemplate();
        Measurement measurement = restTemplate.getForObject("http://api.openweathermap.org/data/2.5/weather?id=3071961&units=metric&APPID=383b888b2b81f8060f1f57453315b655", Measurement.class);

        System.out.println(measurement);

*/
    }

}