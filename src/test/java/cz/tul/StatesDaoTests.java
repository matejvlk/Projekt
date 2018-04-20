package cz.tul;

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
public class StatesDaoTests {

    @Autowired
    private StatesDao statesDao;

    @Test
    public void testStates() {

        statesDao.deleteStates();

        State state = new State("Test state", true, "user");

        assertTrue("State creation should return true", statesDao.create(state));

        List<State> states = statesDao.getAllStates();

        assertEquals("Number of states should be 1.", 1, states.size());

        assertTrue("State should exist.", statesDao.exists(state.getStateName()));

        assertEquals("Created state should be identical to retrieved state",
                state, states.get(0));

    }
}
