package cz.tul;

import cz.tul.data.State;
import cz.tul.service.StateService;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = {App.class})
@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
//@ActiveProfiles({"test"})
//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StatesDaoTests {

    @Autowired
    private StateService stateService;

    private State state1 = new State("Česká republika",true, "ROLE_USER");
    private State state2 = new State("Německo", true, "ROLE_ADMIN");
    private State state3 = new State("USA", true, "ROLE_USER");
    private State state4 = new State("Slovensko", false, "state");

    @Before
    public void init() {
        stateService.deleteStates();
    }


    @Test
    public void testCreateRetrieve() {
        stateService.create(state1);

        List<State> states1 = stateService.getAllStates();

        System.out.println(states1);

        assertEquals("One state should have been created and retrieved", 1, states1.size());

        assertEquals("Inserted state should match retrieved", state1, states1.get(0));

        stateService.create(state2);
        stateService.create(state3);
        stateService.create(state4);

        List<State> states2 = stateService.getAllStates();

        assertEquals("Should be four retrieved states.", 4, states2.size());
    }

    @Test
    public void testExists() {
        stateService.create(state1);
        stateService.create(state2);
        stateService.create(state3);

        assertTrue("State should exist.", stateService.exists(state2.getId()));
        assertFalse("State should not exist.", stateService.exists(16565468));
    }
}
