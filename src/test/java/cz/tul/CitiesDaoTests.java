package cz.tul;

import cz.tul.data.City;
import cz.tul.data.State;
import cz.tul.service.CityService;
import cz.tul.service.StateService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {App.class})
@ActiveProfiles({"test"})
public class CitiesDaoTests {

    @Autowired
    private CityService cityService;

    @Autowired
    private StateService stateService;

    private State state1 = new State("Česká republika", true, "ROLE_USER");
    private State state2 = new State("Německo", true, "ROLE_ADMIN");
    private State state3 = new State("USA", true, "ROLE_USER");
    private State state4 = new State("Slovensko", false, "state");

    private City city1 = new City(state1, "This is a test city.");
    private City city2 = new City(state1, "This is another test city.");
    private City city3 = new City(state2, "This is yet another test city.");
    private City city4 = new City(state3, "This is a test city once again.");
    private City city5 = new City(state3, "Here is an interesting city of some kind.");
    private City city6 = new City(state3, "This is just a test city.");
    private City city7 = new City(state4, "This is a test city for a state that is not enabled.");

    @Before
    public void init() {
        cityService.deleteCities();
        stateService.deleteStates();
    }


    @Test
    public void testDelete() {
        stateService.create(state1);
        stateService.create(state2);
        stateService.create(state3);
        stateService.create(state4);
        cityService.saveOrUpdate(city2);
        cityService.saveOrUpdate(city3);
        cityService.saveOrUpdate(city4);
        cityService.saveOrUpdate(city5);
        cityService.saveOrUpdate(city6);
        cityService.saveOrUpdate(city7);

        City retrieved1 = cityService.getCity(city2.getId());
        assertNotNull("City with ID " + retrieved1.getId() + " should not be null (deleted, actual)", retrieved1);

        cityService.delete(city2.getId());

        City retrieved2 = cityService.getCity(city2.getId());
        assertNull("City with ID " + retrieved1.getId() + " should be null (deleted, actual)", retrieved2);
    }

    @Test
    public void testGetById() {
        stateService.create(state1);
        stateService.create(state2);
        stateService.create(state3);
        stateService.create(state4);
        cityService.saveOrUpdate(city1);
        cityService.saveOrUpdate(city2);
        cityService.saveOrUpdate(city3);
        cityService.saveOrUpdate(city4);
        cityService.saveOrUpdate(city5);
        cityService.saveOrUpdate(city6);
        cityService.saveOrUpdate(city7);

        City retrieved1 = cityService.getCity(city1.getId());
        assertEquals("Cities should match", city1, retrieved1);

        City retrieved2 = cityService.getCity(city7.getId());
        assertNull("Should not retrieve city for disabled state.", retrieved2);
    }

    @Test
    public void testCreateRetrieve() {
        stateService.create(state1);
        stateService.create(state2);
        stateService.create(state3);
        stateService.create(state4);

        cityService.saveOrUpdate(city1);

        List<City> cities1 = cityService.getCities();
        assertEquals("Should be one city.", 1, cities1.size());

        assertEquals("Retrieved city should equal inserted city.", city1, cities1.get(0));

        cityService.saveOrUpdate(city2);
        cityService.saveOrUpdate(city3);
        cityService.saveOrUpdate(city4);
        cityService.saveOrUpdate(city5);
        cityService.saveOrUpdate(city6);
        cityService.saveOrUpdate(city7);

        List<City> cities2 = cityService.getCities();
        assertEquals("Should be six cities for enabled states.", 6, cities2.size());
    }

    @Test
    public void testUpdate() {
        stateService.create(state1);
        stateService.create(state2);
        stateService.create(state3);
        stateService.create(state4);
        cityService.saveOrUpdate(city2);
        cityService.saveOrUpdate(city3);
        cityService.saveOrUpdate(city4);
        cityService.saveOrUpdate(city5);
        cityService.saveOrUpdate(city6);
        cityService.saveOrUpdate(city7);

        city3.setCityName("This city has updated name.");
        cityService.saveOrUpdate(city3);

        City retrieved = cityService.getCity(city3.getId());
        assertEquals("Retrieved city should be updated.", city3, retrieved);
    }

    @Test
    public void testGetStateName() {
        stateService.create(state1);
        stateService.create(state2);
        stateService.create(state3);
        stateService.create(state4);

        cityService.saveOrUpdate(city1);
        cityService.saveOrUpdate(city2);
        cityService.saveOrUpdate(city3);
        cityService.saveOrUpdate(city4);
        cityService.saveOrUpdate(city5);
        cityService.saveOrUpdate(city6);
        cityService.saveOrUpdate(city7);

        List<City> cities1 = cityService.getCitiesByName(state3.getStateName());
        assertEquals("Should be three cities for this state.", 3, cities1.size());

        //List<City> cities2 = cityService.getCities("sdfsfd");
        //assertEquals("Should be zero cities for this state.", 0, cities2.size());

        List<City> cities3 = cityService.getCitiesByName(state2.getStateName());
        assertEquals("Should be 1 city for this state.", 1, cities3.size());
    }
}
