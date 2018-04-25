
package cz.tul.app;

import cz.tul.data.City;
import cz.tul.data.State;
import cz.tul.service.CityService;
import cz.tul.service.StateService;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
//import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import java.util.List;

@SpringBootApplication
@EnableTransactionManagement
//@EntityScan("cz.tul")
public class Main {

    @Bean
    public CityService cityService() {
        return new CityService();
    }

    @Bean
    public StateService stateService() {
        return new StateService();
    }

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Main.class);
        ApplicationContext ctx = app.run(args);

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
}