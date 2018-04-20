package cz.tul;

import cz.tul.app.Main;
import cz.tul.data.State;
import cz.tul.data.StatesDao;
import org.junit.FixMethodOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Main.class})
@ActiveProfiles({"test"})
public class StatesDaoTests {

    @Autowired
    private StatesDao statesDao;

    private State state1 = new State("Česká republika",true, "ROLE_USER");
    private State state2 = new State("Německo", true, "ROLE_ADMIN");
    private State state3 = new State("USA", true, "ROLE_USER");
    private State state4 = new State("Slovensko", false, "state");

    @Before
    public void init() {
        statesDao.deleteStates();
    }


    @Test
    public void testCreateRetrieve() {
        statesDao.create(state1);

        List<State> states1 = statesDao.getAllStates();

        System.out.println(states1);

        assertEquals("One state should have been created and retrieved", 1, states1.size());

        assertEquals("Inserted state should match retrieved", state1, states1.get(0));

        statesDao.create(state2);
        statesDao.create(state3);
        statesDao.create(state4);

        List<State> states2 = statesDao.getAllStates();

        assertEquals("Should be four retrieved states.", 4, states2.size());
    }

    @Test
    public void testExists() {
        statesDao.create(state1);
        statesDao.create(state2);
        statesDao.create(state3);

        assertTrue("State should exist.", statesDao.exists(state2.getStateName()));
        assertFalse("State should not exist.", statesDao.exists("xkjhsfjlsjf"));
    }
}
