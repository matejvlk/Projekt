package cz.tul;

import cz.tul.data.City;
import cz.tul.data.CitiesDao;
import cz.tul.data.State;
import cz.tul.data.StatesDao;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Main.class})
@ActiveProfiles({"test"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CitiesDaoTests {

    @Autowired
    private CitiesDao citiesDao;

    @Autowired
    private StatesDao statesDao;


    @Test
    public void Test1_createCity() {

        //usersDao.deleteUsers();

        State state = new State("Česká republika", true, "user");

        assertTrue("State creation should return true", statesDao.create(state));

        City city = new City(state, "This is a test city.");

        assertTrue("City creation should return true", citiesDao.create(city));
    }

    @Test
    public void Test2_listCities() {

        List<City> cities = citiesDao.getCities();
        // Get the city with ID filled in.
        City city = cities.get(0);

        assertEquals("Should be one city in database.", 1, cities.size());

        assertEquals("Retrieved city should match created city.", city, cities.get(0));
    }

    @Test
    public void Test3_updateCity() {

        List<City> cities = citiesDao.getCities();

        // Get the city with ID filled in.
        City city = cities.get(0);

        city.setCityName("Updated city name.");
        assertTrue("Offer update should return true", citiesDao.update(city));

        City updated = citiesDao.getCity(city.getId());

        assertEquals("Updated city should match retrieved updated city", city, updated);
    }

    @Test
    public void Test4_getOfferById() {

        List<State> states = statesDao.getAllStates();

        // Test get by ID ///////
        City city2 = new City(states.get(0), "This is a test city.");

        assertTrue("Offer creation should return true", citiesDao.create(city2));

        List<City> stateCities = citiesDao.getCities();
        assertEquals("Should be two cities for state.", 2, stateCities.size());

        List<City> secondList = citiesDao.getCities();
        System.out.println(secondList);

        for (City current : secondList) {
            City retrieved = citiesDao.getCity(current.getId());

            assertEquals("City by ID should match city from list.", current, retrieved);
        }
    }

    @Test
    public void Test5_deleteOffer() {

        List<City> cities = citiesDao.getCities();

        // Get the city with ID filled in.
        City city = cities.get(0);

        // Test deletion
        citiesDao.delete(city.getId());

        List<City> finalList = citiesDao.getCities();

        assertEquals("Offers lists should contain one city.", 1, finalList.size());
    }

}
