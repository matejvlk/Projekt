package cz.tul.app;

import cz.tul.data.City;
import cz.tul.data.CitiesDao;
import cz.tul.data.State;
import cz.tul.data.StatesDao;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import java.util.List;

@SpringBootApplication
@EnableTransactionManagement
@EntityScan("cz.tul.data")
public class Main {

    @Bean
    public CitiesDao citiesDao() {
        return new CitiesDao();
    }

    @Bean
    public StatesDao statesDao() {
        return new StatesDao();
    }

    @Autowired
    EntityManagerFactory entityManagerFactory;

    @Bean
    public SessionFactory sessionFactory() {
        return entityManagerFactory.unwrap(SessionFactory.class);
    }

    @Bean
    public PlatformTransactionManager txManager() {
        return new HibernateTransactionManager(entityManagerFactory.unwrap(SessionFactory.class));
    }

    public static void main(String[] args) {
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
        citiesDao.saveOrUpdate(city);

        List<City> cities = citiesDao.getCities();
        System.out.println(cities);
    }
}