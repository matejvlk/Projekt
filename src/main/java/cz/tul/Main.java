package cz.tul;

import cz.tul.data.City;
import cz.tul.data.State;
import cz.tul.data.CitiesDao;
import cz.tul.data.StatesDao;
import cz.tul.provisioning.Provisioner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import java.util.List;

@SpringBootApplication
public class Main {

    @Bean
    public CitiesDao citiesDao() {
        return new CitiesDao();
    }

    @Bean
    public StatesDao statesDao() {
        return new StatesDao();
    }

    @Profile({"devel", "test"})
    @Bean(initMethod = "doProvision")
    public Provisioner provisioner() {
        return new Provisioner();
    }

    public static void main(String[] args) throws Exception {

        SpringApplication app = new SpringApplication(Main.class);
        ApplicationContext ctx = app.run(args);

        //states
        StatesDao statesDao = ctx.getBean(StatesDao.class);

        State state = new State("Czech", true, "user");
        statesDao.create(state);

        State state2 = new State("USA", true, "user");
        statesDao.create(state2);

        List<State> states = statesDao.getAllStates();
        System.out.println(states);

        //cities

        CitiesDao citiesDao = ctx.getBean(CitiesDao.class);

        City city = new City(state, "Liberec");
        citiesDao.create(city);

        List<City> cities = citiesDao.getCities();
        System.out.println(cities);

    }
}